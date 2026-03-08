package com.example.signalstrength.data.repository

import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.data.local.RoomDao
import com.example.signalstrength.data.local.RoomEntity
import com.example.signalstrength.data.local.WifiReadingDao
import com.example.signalstrength.data.remote.supabase.SupabaseRoomDataSource
import com.example.signalstrength.domain.model.Room
import com.example.signalstrength.domain.model.RoomWithStats
import java.time.Instant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val roomDao: RoomDao,
    private val wifiReadingDao: WifiReadingDao,
    private val supabaseRoomDataSource: SupabaseRoomDataSource,
    private val userPreferencesDataStore: UserPreferencesDataStore
) {
    /**
     * Flow of all rooms (for current user) combined with their aggregate stats.
     * Re-emits whenever rooms or readings change.
     */
    fun getRoomsWithStatsFlow(userId: String): Flow<List<RoomWithStats>> =
        combine(
            roomDao.getAllRoomsFlow(userId),
            wifiReadingDao.getAllReadingsFlow()
        ) { rooms, allReadings ->
            rooms.map { roomEntity ->
                val roomReadings = allReadings.filter { it.roomId == roomEntity.id }
                val rssiValues = roomReadings.mapNotNull { it.wifiRssiDbm }
                RoomWithStats(
                    room = roomEntity.toDomain(),
                    readingCount = roomReadings.size,
                    avgRssi = if (rssiValues.isEmpty()) null else rssiValues.average(),
                    latestRssi = roomReadings.maxByOrNull { it.timestampMs }?.wifiRssiDbm,
                    minRssi = rssiValues.minOrNull(),
                    maxRssi = rssiValues.maxOrNull()
                )
            }
        }

    /**
     * Returns the local Room DB id for the given room name (creates it if it doesn't exist).
     */
    suspend fun getOrCreate(name: String): Long {
        val userId = userPreferencesDataStore.userIdFlow.first() ?: error("Not logged in")
        val existing = roomDao.getByName(userId, name)
        if (existing != null) return existing.id
        return roomDao.insertRoom(RoomEntity(userId = userId, name = name))
    }

    /**
     * Syncs any rooms that haven't yet been pushed to Supabase.
     * Called before syncing wifi_samples so the FK is available.
     */
    suspend fun syncRooms() {
        val userId = userPreferencesDataStore.userIdFlow.first() ?: return
        val unsynced = roomDao.getUnsynced()
        for (room in unsynced) {
            try {
                val supabaseId = supabaseRoomDataSource.createRoom(userId, room.name)
                roomDao.updateSupabaseId(room.id, supabaseId)
            } catch (_: Exception) {
                // Non-fatal: will retry on next sync
            }
        }
    }

    suspend fun getByLocalId(id: Long): Room? =
        roomDao.getById(id)?.toDomain()

    private fun RoomEntity.toDomain() = Room(
        id = id,
        userId = userId,
        name = name,
        createdAt = Instant.ofEpochMilli(createdAtMs)
    )
}
