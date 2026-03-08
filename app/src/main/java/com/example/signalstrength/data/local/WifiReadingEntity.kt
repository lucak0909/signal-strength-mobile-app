package com.example.signalstrength.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.signalstrength.data.remote.supabase.WifiSampleDto
import com.example.signalstrength.domain.model.WifiReading
import java.time.Instant

@Entity(tableName = "wifi_readings")
data class WifiReadingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: String,
    val wifiRssiDbm: Int?,
    val linkSpeedMbps: Int?,
    val isConnected: Boolean,
    val timestampMs: Long,          // epoch ms
    val syncedToSupabase: Boolean = false,
    val roomId: Long? = null        // FK to local rooms.id; null = untagged reading
)

fun WifiReadingEntity.toDomain(): WifiReading = WifiReading(
    id = id,
    userId = userId,
    wifiRssiDbm = wifiRssiDbm,
    linkSpeedMbps = linkSpeedMbps,
    isConnected = isConnected,
    timestamp = Instant.ofEpochMilli(timestampMs),
    roomId = roomId
)

/** supabaseRoomId is the Supabase-side FK, resolved from RoomEntity.supabaseId at sync time */
fun WifiReadingEntity.toSupabaseDto(supabaseRoomId: Long? = null): WifiSampleDto = WifiSampleDto(
    userId = userId,
    wifiRssiDbm = wifiRssiDbm,
    linkSpeedMbps = linkSpeedMbps,
    isConnected = isConnected,
    ts = Instant.ofEpochMilli(timestampMs).toString(),
    roomId = supabaseRoomId
)
