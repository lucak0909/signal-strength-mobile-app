package com.example.signalstrength.di

import android.content.Context
import androidx.room.Room
import com.example.signalstrength.BuildConfig
import com.example.signalstrength.data.local.AppDatabase
import com.example.signalstrength.data.local.RoomDao
import com.example.signalstrength.data.local.WifiReadingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "signal_strength_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideWifiReadingDao(database: AppDatabase): WifiReadingDao =
        database.wifiReadingDao()

    @Provides
    fun provideRoomDao(database: AppDatabase): RoomDao =
        database.roomDao()

    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient =
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
        }
}
