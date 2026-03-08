package com.example.signalstrength.data.remote.supabase

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WifiSampleDto(
    @SerialName("user_id") val userId: String,
    @SerialName("wifi_rssi_dbm") val wifiRssiDbm: Int?,
    @SerialName("link_speed_mbps") val linkSpeedMbps: Int?,
    @SerialName("is_connected") val isConnected: Boolean,
    @SerialName("ts") val ts: String,  // Instant.toString() → ISO-8601
    @SerialName("room_id") val roomId: Long? = null  // FK to public.rooms.id; null if untagged
)
