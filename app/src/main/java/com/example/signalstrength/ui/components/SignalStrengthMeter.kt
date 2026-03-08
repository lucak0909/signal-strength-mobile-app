package com.example.signalstrength.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SignalStrengthMeter(
    rssiDbm: Int?,
    modifier: Modifier = Modifier
) {
    // Map dBm range [-100, -30] → [0f, 1f]
    val progress = if (rssiDbm != null) {
        ((rssiDbm - (-100f)) / ((-30f) - (-100f))).coerceIn(0f, 1f)
    } else 0f

    val barColor = when {
        rssiDbm == null  -> Color.Gray
        rssiDbm < -80    -> Color(0xFFD32F2F)   // Red — poor
        rssiDbm < -60    -> Color(0xFFFFA000)   // Orange — fair
        else             -> Color(0xFF4CAF50)   // Green — good
    }

    Column(modifier = modifier) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = barColor,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = if (rssiDbm != null) "$rssiDbm dBm" else "No signal",
            style = MaterialTheme.typography.bodySmall,
            color = barColor
        )
    }
}
