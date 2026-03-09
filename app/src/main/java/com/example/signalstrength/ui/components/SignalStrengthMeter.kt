package com.example.signalstrength.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private fun rssiQuality(rssi: Int?) = when {
    rssi == null -> "No Signal"
    rssi > -60   -> "Excellent"
    rssi > -70   -> "Good"
    rssi > -80   -> "Fair"
    else         -> "Poor"
}

private fun rssiColor(rssi: Int?) = when {
    rssi == null -> Color.Gray
    rssi > -60   -> Color(0xFF4CAF50)   // Green — excellent/good
    rssi > -70   -> Color(0xFF8BC34A)   // Light green — good
    rssi > -80   -> Color(0xFFFFA000)   // Orange — fair
    else         -> Color(0xFFD32F2F)   // Red — poor
}

@Composable
fun SignalStrengthMeter(
    rssiDbm: Int?,
    modifier: Modifier = Modifier
) {
    val progress = if (rssiDbm != null) {
        ((rssiDbm - (-100f)) / ((-30f) - (-100f))).coerceIn(0f, 1f)
    } else 0f

    val barColor = rssiColor(rssiDbm)
    val quality  = rssiQuality(rssiDbm)

    Column(modifier = modifier) {
        // Quality label row
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = quality,
                style = MaterialTheme.typography.titleLarge,
                color = barColor
            )
            rssiDbm?.let {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$it dBm",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Thicker rounded progress bar
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = barColor,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}
