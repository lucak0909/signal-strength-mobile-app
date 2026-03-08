package com.example.signalstrength.data.remote.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Supabase row DTOs (for decoding query results)
@Serializable
data class UserRow(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String
)

@Serializable
data class PasswordRow(
    @SerialName("password_enc") val passwordEnc: String
)

// Insert DTOs
@Serializable
data class UserInsert(
    @SerialName("email") val email: String
)

@Serializable
data class PasswordInsert(
    @SerialName("user_id") val userId: String,
    @SerialName("password_enc") val passwordEnc: String
)

// Request/response used by AuthRepository
data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val email: String, val password: String)
data class AuthResponse(val userId: String, val email: String)
