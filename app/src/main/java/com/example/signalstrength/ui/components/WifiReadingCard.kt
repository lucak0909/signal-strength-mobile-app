package com.example.signalstrength.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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

private fun rssiQuality(rssi: Int?) = when {
    rssi == null -> "No Signal"
    rssi > -60   -> "Excellent"
    rssi > -70   -> "Good"
    rssi > -80   -> "Fair"
    else         -> "Poor"
}

@Composable
fun WifiReadingCard(
    reading: WifiReading,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rssiColor = when {
        reading.wifiRssiDbm == null -> Color.Gray
        reading.wifiRssiDbm > -60  -> Color(0xFF4CAF50)
        reading.wifiRssiDbm > -70  -> Color(0xFF8BC34A)
        reading.wifiRssiDbm > -80  -> Color(0xFFFFA000)
        else                       -> Color(0xFFD32F2F)
    }
    val quality = rssiQuality(reading.wifiRssiDbm)
    val connectedColor = if (reading.isConnected) Color(0xFF4CAF50) else Color(0xFFD32F2F)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Colored left accent stripe
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(rssiColor)
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth(),
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
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = if (reading.isConnected) "Connected" else "Disconnected",
                        style = MaterialTheme.typography.labelSmall,
                        color = connectedColor
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = if (reading.wifiRssiDbm != null) "${reading.wifiRssiDbm} dBm" else "N/A",
                        style = MaterialTheme.typography.titleMedium,
                        color = rssiColor
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = quality,
                        style = MaterialTheme.typography.labelSmall,
                        color = rssiColor
                    )
                }
            }
        }
    }
}
