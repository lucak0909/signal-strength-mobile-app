package com.example.signalstrength.ui.screens.rooms

import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomsScreen(
    onNavigateToDetail: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: RoomsViewModel = hiltViewModel()
) {
    val rooms by viewModel.rooms.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rooms") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (rooms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No rooms yet.\n\nStart a collection session from the Dashboard and enter a room name to build signal profiles.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(rooms, key = { it.room.id }) { rws ->
                    RoomCard(rws = rws, onClick = { onNavigateToDetail(rws.room.id) })
                }
            }
        }
    }
}

@Composable
private fun RoomCard(rws: RoomWithStats, onClick: () -> Unit) {
    val avgColor = when {
        rws.avgRssi == null    -> Color.Gray
        rws.avgRssi < -80      -> Color(0xFFD32F2F)
        rws.avgRssi < -60      -> Color(0xFFFFA000)
        else                   -> Color(0xFF4CAF50)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(rws.room.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    label = "Readings",
                    value = rws.readingCount.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Avg RSSI",
                    value = if (rws.avgRssi != null) "${"%.1f".format(rws.avgRssi)} dBm" else "N/A",
                    valueColor = avgColor,
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Latest",
                    value = if (rws.latestRssi != null) "${rws.latestRssi} dBm" else "N/A",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    label = "Best",
                    value = if (rws.maxRssi != null) "${rws.maxRssi} dBm" else "N/A",
                    valueColor = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = "Worst",
                    value = if (rws.minRssi != null) "${rws.minRssi} dBm" else "N/A",
                    valueColor = Color(0xFFD32F2F),
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Color.Unspecified
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = valueColor
        )
    }
}
