package com.example.signalstrength.ui.screens.roomdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.signalstrength.domain.model.RoomWithStats
import com.example.signalstrength.ui.components.WifiReadingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailScreen(
    onNavigateBack: () -> Unit,
    onNavigateToReadingDetail: (Long) -> Unit,
    viewModel: RoomDetailViewModel = hiltViewModel()
) {
    val stats    by viewModel.stats.collectAsStateWithLifecycle()
    val readings by viewModel.readings.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stats?.room?.name ?: "Room") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (readings.isEmpty() && stats == null) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { Text("Loading…") }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Stats card
                item {
                    stats?.let { StatsCard(it) }
                }

                // Readings
                if (readings.isNotEmpty()) {
                    item {
                        Text(
                            "Readings (${readings.size})",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    items(readings, key = { it.id }) { reading ->
                        WifiReadingCard(
                            reading = reading,
                            onClick = { onNavigateToReadingDetail(reading.id) }
                        )
                    }
                } else {
                    item {
                        Text(
                            "No readings yet for this room.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatsCard(rws: RoomWithStats) {
    val avgColor = when {
        rws.avgRssi == null -> Color.Gray
        rws.avgRssi < -80   -> Color(0xFFD32F2F)
        rws.avgRssi < -60   -> Color(0xFFFFA000)
        else                -> Color(0xFF4CAF50)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Signal Profile", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileStat("Readings", rws.readingCount.toString())
                ProfileStat(
                    "Average",
                    if (rws.avgRssi != null) "${"%.1f".format(rws.avgRssi)} dBm" else "N/A",
                    valueColor = avgColor
                )
                ProfileStat(
                    "Best",
                    if (rws.maxRssi != null) "${rws.maxRssi} dBm" else "N/A",
                    valueColor = Color(0xFF4CAF50)
                )
                ProfileStat(
                    "Worst",
                    if (rws.minRssi != null) "${rws.minRssi} dBm" else "N/A",
                    valueColor = Color(0xFFD32F2F)
                )
            }
        }
    }
}

@Composable
private fun ProfileStat(
    label: String,
    value: String,
    valueColor: Color = Color.Unspecified
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSecondaryContainer)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = valueColor)
    }
}
