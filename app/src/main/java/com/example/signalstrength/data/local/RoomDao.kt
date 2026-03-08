package com.example.signalstrength.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert
    suspend fun insertRoom(room: RoomEntity): Long

    @Update
    suspend fun updateRoom(room: RoomEntity)

    @Query("SELECT * FROM rooms WHERE userId = :userId ORDER BY name ASC")
    fun getAllRoomsFlow(userId: String): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE id = :id")
    suspend fun getById(id: Long): RoomEntity?

    @Query("SELECT * FROM rooms WHERE userId = :userId AND name = :name LIMIT 1")
    suspend fun getByName(userId: String, name: String): RoomEntity?

    @Query("UPDATE rooms SET supabaseId = :supabaseId WHERE id = :localId")
    suspend fun updateSupabaseId(localId: Long, supabaseId: Long)

    @Query("SELECT * FROM rooms WHERE supabaseId IS NULL")
    suspend fun getUnsynced(): List<RoomEntity>
}
