package com.example.signalstrength.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val detailFormatter = DateTimeFormatter
    .ofPattern("EEEE, MMM d yyyy  HH:mm:ss")
    .withZone(ZoneId.systemDefault())

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingDetailScreen(
    onNavigateBack: () -> Unit,
    viewModel: ReadingDetailViewModel = hiltViewModel()
) {
    val reading by viewModel.reading.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reading Detail") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        reading?.let { r ->
            val rssiColor = when {
                r.wifiRssiDbm == null -> Color.Gray
                r.wifiRssiDbm < -80  -> Color(0xFFD32F2F)
                r.wifiRssiDbm < -60  -> Color(0xFFFFA000)
                else                 -> Color(0xFF4CAF50)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        DetailRow("Reading ID", r.id.toString())
                        DetailRow("Timestamp", detailFormatter.format(r.timestamp))
                        DetailRow(
                            "Connection",
                            if (r.isConnected) "Connected" else "Disconnected",
                            valueColor = if (r.isConnected) Color(0xFF4CAF50) else Color(0xFFD32F2F)
                        )
                        DetailRow(
                            "RSSI",
                            if (r.wifiRssiDbm != null) "${r.wifiRssiDbm} dBm" else "N/A",
                            valueColor = rssiColor
                        )
                        DetailRow(
                            "Link Speed",
                            if (r.linkSpeedMbps != null) "${r.linkSpeedMbps} Mbps" else "N/A"
                        )
                    }
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueColor: Color = Color.Unspecified
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = valueColor
        )
    }
}
