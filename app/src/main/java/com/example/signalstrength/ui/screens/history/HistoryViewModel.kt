package com.example.signalstrength.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.repository.WifiRepository
import com.example.signalstrength.domain.model.WifiReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    wifiRepository: WifiRepository
) : ViewModel() {

    val readings: StateFlow<List<WifiReading>> = wifiRepository.getAllReadingsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
