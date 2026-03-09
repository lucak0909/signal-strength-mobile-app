package com.example.signalstrength.ui.screens.dashboard

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.signalstrength.ui.components.SignalStrengthMeter

private fun signalQuality(rssi: Int?) = when {
    rssi == null -> "No Signal"
    rssi > -60   -> "Excellent"
    rssi > -70   -> "Good"
    rssi > -80   -> "Fair"
    else         -> "Poor"
}

private fun signalColor(rssi: Int?) = when {
    rssi == null -> Color.Gray
    rssi > -60   -> Color(0xFF4CAF50)
    rssi > -70   -> Color(0xFF8BC34A)
    rssi > -80   -> Color(0xFFFFA000)
    else         -> Color(0xFFD32F2F)
}

@Composable
fun DashboardScreen(
    onNavigateToHistory: () -> Unit,
    onNavigateToRooms: () -> Unit,
    onLogout: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isServiceRunning by viewModel.isServiceRunning.collectAsStateWithLifecycle()
    val latestReading    by viewModel.latestReading.collectAsStateWithLifecycle()

    var showRoomDialog by rememberSaveable { mutableStateOf(false) }
    var roomNameInput  by rememberSaveable { mutableStateOf("") }

    val rssi    = latestReading?.wifiRssiDbm
    val quality = signalQuality(rssi)
    val color   = signalColor(rssi)

    // Request ACCESS_FINE_LOCATION before starting the service
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) showRoomDialog = true
    }

    if (showRoomDialog) {
        AlertDialog(
            onDismissRequest = { showRoomDialog = false; roomNameInput = "" },
            title   = { Text("Start Collection") },
            text    = {
                Column {
                    Text(
                        "Optionally enter a room name to tag this session's readings.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = roomNameInput,
                        onValueChange = { roomNameInput = it },
                        label = { Text("Room name (optional)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.startCollection(context, roomNameInput.ifBlank { null })
                    showRoomDialog = false
                    roomNameInput = ""
                }) { Text("Start") }
            },
            dismissButton = {
                TextButton(onClick = { showRoomDialog = false; roomNameInput = "" }) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = {
                viewModel.logout()
                onLogout()
            }) { Text("Logout") }
        }

        Spacer(Modifier.height(24.dp))

        // Live signal card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Live WiFi Signal",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(12.dp))

                // Large quality label — the focal point
                Text(
                    text = quality,
                    style = MaterialTheme.typography.displaySmall,
                    color = color
                )

                // RSSI value + link speed on same row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = if (rssi != null) "$rssi dBm" else "—",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    latestReading?.linkSpeedMbps?.let { speed ->
                        Text(
                            text = "$speed Mbps",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Meter as visual reinforcement
                SignalStrengthMeter(
                    rssiDbm = rssi,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Start / Stop collection button
        Button(
            onClick = {
                if (isServiceRunning) {
                    viewModel.stopCollection(context)
                } else {
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context, Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) showRoomDialog = true
                    else permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isServiceRunning) MaterialTheme.colorScheme.error
                                 else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isServiceRunning) "Stop Collection" else "Start Collection")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onNavigateToHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View History")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = onNavigateToRooms,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Rooms")
        }
    }
}
