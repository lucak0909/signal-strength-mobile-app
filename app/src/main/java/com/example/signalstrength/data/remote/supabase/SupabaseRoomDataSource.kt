package com.example.signalstrength.data.remote.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseRoomDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    /**
     * Insert a room (user_id + name) and return the Supabase-assigned id.
     * Falls back to SELECT if the room already exists (UNIQUE(user_id, name) conflict).
     */
    suspend fun createRoom(userId: String, name: String): Long {
        return try {
            val row = client.postgrest["rooms"]
                .insert(RoomInsertDto(userId = userId, name = name)) {
                    select()
                }
                .decodeSingle<RoomRow>()
            row.id
        } catch (e: Exception) {
            // Room already exists — query for the existing id
            val existing = client.postgrest["rooms"]
                .select {
                    filter {
                        eq("user_id", userId)
                        eq("name", name)
                    }
                }
                .decodeList<RoomRow>()
            existing.firstOrNull()?.id ?: throw Exception("Failed to create or find room: ${e.message}")
        }
    }
}
