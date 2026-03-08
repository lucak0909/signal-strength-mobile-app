package com.example.signalstrength.data.remote.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    suspend fun insertReading(dto: WifiSampleDto): Boolean {
        return try {
            client.postgrest["wifi_samples"].insert(dto)
            true
        } catch (e: Exception) {
            false
        }
    }
}
