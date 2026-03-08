package com.example.signalstrength.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.signalstrength.domain.model.WifiReading
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val cardFormatter = DateTimeFormatter
    .ofPattern("MMM d, HH:mm:ss")
    .withZone(ZoneId.systemDefault())

@Composable
fun WifiReadingCard(
    reading: WifiReading,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rssiColor = when {
        reading.wifiRssiDbm == null -> Color.Gray
        reading.wifiRssiDbm < -80  -> Color(0xFFD32F2F)
        reading.wifiRssiDbm < -60  -> Color(0xFFFFA000)
        else                       -> Color(0xFF4CAF50)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cardFormatter.format(reading.timestamp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = if (reading.linkSpeedMbps != null) "${reading.linkSpeedMbps} Mbps" else "Speed: N/A",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = if (reading.wifiRssiDbm != null) "${reading.wifiRssiDbm} dBm" else "N/A",
                    style = MaterialTheme.typography.titleMedium,
                    color = rssiColor
                )
                SuggestionChip(
                    onClick = {},
                    label = { Text(if (reading.isConnected) "Connected" else "Disconnected") },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = if (reading.isConnected) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    )
                )
            }
        }
    }
}
