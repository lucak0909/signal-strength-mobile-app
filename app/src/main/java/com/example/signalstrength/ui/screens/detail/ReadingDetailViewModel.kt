package com.example.signalstrength.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.repository.WifiRepository
import com.example.signalstrength.domain.model.WifiReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadingDetailViewModel @Inject constructor(
    private val wifiRepository: WifiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Hilt auto-populates SavedStateHandle from nav arguments
    private val readingId: Long = checkNotNull(savedStateHandle["readingId"])

    private val _reading = MutableStateFlow<WifiReading?>(null)
    val reading: StateFlow<WifiReading?> = _reading.asStateFlow()

    init {
        viewModelScope.launch {
            _reading.value = wifiRepository.getReadingById(readingId)
        }
    }
}
