package com.example.signalstrength.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WifiReadingEntity::class, RoomEntity::class],
    version = 2,    // bumped: added RoomEntity + roomId column to WifiReadingEntity
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wifiReadingDao(): WifiReadingDao
    abstract fun roomDao(): RoomDao
}
