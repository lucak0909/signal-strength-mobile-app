package com.example.signalstrength.ui.screens.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.data.repository.RoomRepository
import com.example.signalstrength.domain.model.RoomWithStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val userPreferencesDataStore: UserPreferencesDataStore
) : ViewModel() {

    val rooms: StateFlow<List<RoomWithStats>> = userPreferencesDataStore.userIdFlow
        .flatMapLatest { userId ->
            if (userId.isNullOrEmpty()) flow { emit(emptyList()) }
            else roomRepository.getRoomsWithStatsFlow(userId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
