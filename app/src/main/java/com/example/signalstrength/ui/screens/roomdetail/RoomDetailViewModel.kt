package com.example.signalstrength.ui.screens.roomdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.data.repository.RoomRepository
import com.example.signalstrength.data.repository.WifiRepository
import com.example.signalstrength.domain.model.RoomWithStats
import com.example.signalstrength.domain.model.WifiReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoomDetailViewModel @Inject constructor(
    private val wifiRepository: WifiRepository,
    private val roomRepository: RoomRepository,
    private val userPreferencesDataStore: UserPreferencesDataStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val roomId: Long = checkNotNull(savedStateHandle["roomId"])

    val readings: StateFlow<List<WifiReading>> = wifiRepository.getReadingsByRoom(roomId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val stats: StateFlow<RoomWithStats?> = userPreferencesDataStore.userIdFlow
        .flatMapLatest { userId ->
            if (userId.isNullOrEmpty()) flow { emit(null) }
            else roomRepository.getRoomsWithStatsFlow(userId)
                .flatMapLatest { list -> flow { emit(list.find { it.room.id == roomId }) } }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}
