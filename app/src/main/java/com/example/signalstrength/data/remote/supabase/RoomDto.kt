package com.example.signalstrength.data.remote.supabase

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoomInsertDto(
    @SerialName("user_id") val userId: String,
    @SerialName("name") val name: String
)

@Serializable
data class RoomRow(
    @SerialName("id") val id: Long,
    @SerialName("user_id") val userId: String,
    @SerialName("name") val name: String
)
