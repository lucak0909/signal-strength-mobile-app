package com.example.signalstrength.data.repository

import com.example.signalstrength.data.local.RoomDao
import com.example.signalstrength.data.local.WifiReadingDao
import com.example.signalstrength.data.local.WifiReadingEntity
import com.example.signalstrength.data.local.toDomain
import com.example.signalstrength.data.local.toSupabaseDto
import com.example.signalstrength.data.remote.supabase.SupabaseDataSource
import com.example.signalstrength.domain.model.WifiReading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiRepository @Inject constructor(
    private val dao: WifiReadingDao,
    private val roomDao: RoomDao,
    private val supabaseDataSource: SupabaseDataSource
) {
    fun getAllReadingsFlow(): Flow<List<WifiReading>> =
        dao.getAllReadingsFlow().map { entities -> entities.map { it.toDomain() } }

    suspend fun getReadingById(id: Long): WifiReading? =
        dao.getById(id)?.toDomain()

    suspend fun insertReading(entity: WifiReadingEntity) {
        dao.insertReading(entity)
    }

    fun getReadingsByRoom(roomId: Long): Flow<List<WifiReading>> =
        dao.getReadingsByRoomIdFlow(roomId).map { entities -> entities.map { it.toDomain() } }

    suspend fun syncUnsynced() {
        val unsynced = dao.getUnsynced()
        for (entity in unsynced) {
            // Resolve Supabase room FK — may differ from local roomId (or null if unsynced/untagged)
            val supabaseRoomId = entity.roomId?.let { roomDao.getById(it)?.supabaseId }
            val success = supabaseDataSource.insertReading(entity.toSupabaseDto(supabaseRoomId))
            if (success) dao.markSynced(entity.id)
        }
    }
}
