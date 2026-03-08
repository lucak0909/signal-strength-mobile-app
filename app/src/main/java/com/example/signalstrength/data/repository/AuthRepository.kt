package com.example.signalstrength.data.repository

import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.data.remote.auth.LoginRequest
import com.example.signalstrength.data.remote.auth.RegisterRequest
import com.example.signalstrength.data.remote.auth.SupabaseAuthDataSource
import com.example.signalstrength.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authDataSource: SupabaseAuthDataSource,
    private val dataStore: UserPreferencesDataStore
) {
    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authDataSource.login(LoginRequest(email, password))
            val user = User(response.userId, response.email)
            dataStore.saveUser(user.userId, user.email)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<User> {
        return try {
            val response = authDataSource.register(RegisterRequest(email, password))
            val user = User(response.userId, response.email)
            dataStore.saveUser(user.userId, user.email)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        dataStore.clearUser()
    }
}
