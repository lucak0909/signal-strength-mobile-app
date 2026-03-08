package com.example.signalstrength.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WifiReadingDao {

    @Insert
    suspend fun insertReading(entity: WifiReadingEntity): Long

    @Query("SELECT * FROM wifi_readings ORDER BY timestampMs DESC")
    fun getAllReadingsFlow(): Flow<List<WifiReadingEntity>>

    @Query("SELECT * FROM wifi_readings WHERE id = :id")
    suspend fun getById(id: Long): WifiReadingEntity?

    @Query("SELECT * FROM wifi_readings WHERE syncedToSupabase = 0")
    suspend fun getUnsynced(): List<WifiReadingEntity>

    @Query("UPDATE wifi_readings SET syncedToSupabase = 1 WHERE id = :id")
    suspend fun markSynced(id: Long)

    @Query("SELECT * FROM wifi_readings WHERE roomId = :roomId ORDER BY timestampMs DESC")
    fun getReadingsByRoomIdFlow(roomId: Long): Flow<List<WifiReadingEntity>>
}
