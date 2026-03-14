package com.example.signalstrength.ui.screens.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.repository.AuthRepository
import com.example.signalstrength.data.repository.RoomRepository
import com.example.signalstrength.data.repository.WifiRepository
import com.example.signalstrength.domain.model.WifiReading
import com.example.signalstrength.service.WifiCollectorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val wifiRepository: WifiRepository,
    private val authRepository: AuthRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _isServiceRunning = MutableStateFlow(WifiCollectorService.isRunning)
    val isServiceRunning: StateFlow<Boolean> = _isServiceRunning.asStateFlow()

    // Latest reading = first item from the descending-ordered flow
    val latestReading: StateFlow<WifiReading?> = wifiRepository.getAllReadingsFlow()
        .map { it.firstOrNull() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun startCollection(context: Context, roomName: String? = null) {
        viewModelScope.launch {
            val roomId = if (!roomName.isNullOrBlank()) {
                roomRepository.getOrCreate(roomName.trim())
            } else null
            WifiCollectorService.start(context, roomId)
            _isServiceRunning.value = true
        }
    }

    fun stopCollection(context: Context) {
        WifiCollectorService.stop(context)
        _isServiceRunning.value = false
    }

    fun logout() {
        viewModelScope.launch { authRepository.logout() }
    }
}
