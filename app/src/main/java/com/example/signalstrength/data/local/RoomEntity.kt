package com.example.signalstrength.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: String,
    val name: String,
    val supabaseId: Long? = null,   // null until synced to Supabase
    val createdAtMs: Long = System.currentTimeMillis()
)
