package com.example.signalstrength.domain.model

import java.time.Instant

data class WifiReading(
    val id: Long,
    val userId: String,
    val wifiRssiDbm: Int?,
    val linkSpeedMbps: Int?,
    val isConnected: Boolean,
    val timestamp: Instant,  // java.time.Instant (API 26+)
    val roomId: Long? = null  // local Room DB FK; null if reading is not tagged to a room
)
