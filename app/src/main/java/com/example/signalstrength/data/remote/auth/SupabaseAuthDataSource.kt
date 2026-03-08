package com.example.signalstrength.data.remote.auth

import android.util.Base64
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseAuthDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    // ---- PBKDF2-SHA256 helpers ----
    // Hash format: pbkdf2_sha256$200000$<base64url_salt_no_padding>$<base64url_digest_no_padding>
    // Must match Python: hashlib.pbkdf2_hmac('sha256', pwd.encode(), salt, 200000)
    //                    base64.urlsafe_b64encode(data).rstrip(b'=')

    private fun hashPassword(password: String, salt: ByteArray): String {
        val spec = PBEKeySpec(password.toCharArray(), salt, 200_000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        val saltB64 = encodeB64(salt)
        val hashB64 = encodeB64(hash)
        return "pbkdf2_sha256\$200000\$$saltB64\$$hashB64"
    }

    private fun verifyPassword(password: String, stored: String): Boolean {
        val parts = stored.split("$")
        if (parts.size != 4 || parts[0] != "pbkdf2_sha256") return false
        val iterations = parts[1].toIntOrNull() ?: return false
        val salt = decodeB64(parts[2])
        val expectedHash = decodeB64(parts[3])

        val spec = PBEKeySpec(password.toCharArray(), salt, iterations, expectedHash.size * 8)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val actualHash = factory.generateSecret(spec).encoded

        // Constant-time comparison
        if (actualHash.size != expectedHash.size) return false
        var diff = 0
        for (i in actualHash.indices) diff = diff or (actualHash[i].toInt() xor expectedHash[i].toInt())
        return diff == 0
    }

    /** URL-safe base64 encode, no padding — matches Python base64.urlsafe_b64encode(...).rstrip(b'=') */
    private fun encodeB64(data: ByteArray): String =
        Base64.encodeToString(data, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)

    /** URL-safe base64 decode — Android decoder handles missing padding */
    private fun decodeB64(s: String): ByteArray =
        Base64.decode(s, Base64.URL_SAFE)

    // ---- Auth operations ----

    suspend fun login(request: LoginRequest): AuthResponse {
        val users = client.postgrest["users"]
            .select {
                filter { eq("email", request.email) }
            }
            .decodeList<UserRow>()

        val user = users.firstOrNull()
            ?: throw Exception("Invalid email or password")

        val passwords = client.postgrest["passwords"]
            .select {
                filter { eq("user_id", user.id) }
            }
            .decodeList<PasswordRow>()

        val passwordRow = passwords.firstOrNull()
            ?: throw Exception("Invalid email or password")

        if (!verifyPassword(request.password, passwordRow.passwordEnc)) {
            throw Exception("Invalid email or password")
        }

        return AuthResponse(userId = user.id, email = user.email)
    }

    suspend fun register(request: RegisterRequest): AuthResponse {
        // Check email uniqueness
        val existing = client.postgrest["users"]
            .select {
                filter { eq("email", request.email) }
            }
            .decodeList<UserRow>()

        if (existing.isNotEmpty()) throw Exception("Email already registered")

        // Insert user and get back the generated UUID
        val newUser = client.postgrest["users"]
            .insert(UserInsert(email = request.email)) {
                select()
            }
            .decodeSingle<UserRow>()

        // Hash password with fresh random salt
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
        val passwordHash = hashPassword(request.password, salt)

        client.postgrest["passwords"]
            .insert(PasswordInsert(userId = newUser.id, passwordEnc = passwordHash))

        return AuthResponse(userId = newUser.id, email = newUser.email)
    }
}
