package com.example.signalstrength.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.signalstrength.R
import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.data.local.WifiReadingEntity
import com.example.signalstrength.data.repository.WifiRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class WifiCollectorService : LifecycleService() {

    @Inject lateinit var wifiRepository: WifiRepository
    @Inject lateinit var userPreferencesDataStore: UserPreferencesDataStore

    private lateinit var connectivityManager: ConnectivityManager
    private var collectionJob: Job? = null

    companion object {
        const val ACTION_START  = "com.example.signalstrength.ACTION_START"
        const val ACTION_STOP   = "com.example.signalstrength.ACTION_STOP"
        const val EXTRA_ROOM_ID = "extra_room_id"
        private const val NO_ROOM      = -1L
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "wifi_collector_channel"

        fun start(context: Context, roomId: Long? = null) {
            context.startForegroundService(
                Intent(context, WifiCollectorService::class.java).apply {
                    action = ACTION_START
                    putExtra(EXTRA_ROOM_ID, roomId ?: NO_ROOM)
                }
            )
        }

        fun stop(context: Context) {
            context.startService(
                Intent(context, WifiCollectorService::class.java).apply { action = ACTION_STOP }
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_START -> {
                val raw = intent.getLongExtra(EXTRA_ROOM_ID, NO_ROOM)
                startCollection(if (raw == NO_ROOM) null else raw)
            }
            ACTION_STOP  -> stopCollection()
        }
        return START_STICKY
    }

    private fun startCollection(roomId: Long? = null) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .setSmallIcon(R.drawable.ic_notification)
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        collectionJob = lifecycleScope.launch {
            // Read userId once — if missing, stop immediately
            val userId = userPreferencesDataStore.userIdFlow.first()
            if (userId.isNullOrEmpty()) {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                return@launch
            }

            while (true) {
                collectAndStore(userId, roomId)
                delay(30_000L)
            }
        }
    }

    private fun stopCollection() {
        collectionJob?.cancel()
        collectionJob = null
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private suspend fun collectAndStore(userId: String, roomId: Long? = null) {
        val caps = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val isConnected = caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        // transportInfo as WifiInfo requires ACCESS_FINE_LOCATION (granted before service starts)
        val wifiInfo = caps?.transportInfo as? WifiInfo
        val rssi      = if (isConnected) wifiInfo?.rssi      else null
        val linkSpeed = if (isConnected) wifiInfo?.linkSpeed else null

        val entity = WifiReadingEntity(
            userId        = userId,
            wifiRssiDbm   = rssi,
            linkSpeedMbps = linkSpeed,
            isConnected   = isConnected,
            timestampMs   = System.currentTimeMillis(),
            roomId        = roomId
        )

        withContext(Dispatchers.IO) {
            wifiRepository.insertReading(entity)
            wifiRepository.syncUnsynced()
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_LOW
        )
        (getSystemService(NotificationManager::class.java)).createNotificationChannel(channel)
    }
}
