package com.example.signalstrength.domain.model

import java.time.Instant

data class Room(
    val id: Long,
    val userId: String,
    val name: String,
    val createdAt: Instant
)

data class RoomWithStats(
    val room: Room,
    val readingCount: Int,
    val avgRssi: Double?,    // null if no connected readings with RSSI
    val latestRssi: Int?,
    val minRssi: Int?,
    val maxRssi: Int?
)
