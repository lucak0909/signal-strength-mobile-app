package com.example.signalstrength

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.signalstrength.data.datastore.UserPreferencesDataStore
import com.example.signalstrength.ui.navigation.AppNavGraph
import com.example.signalstrength.ui.navigation.Screen
import com.example.signalstrength.ui.theme.SignalStrengthTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userPreferencesDataStore: UserPreferencesDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Read initial login state synchronously before rendering — DataStore is fast (single file read).
        // Avoids a visible Auth→Dashboard flash for already-logged-in users.
        val startDestination = runBlocking {
            if (userPreferencesDataStore.isLoggedInFlow.first()) Screen.Dashboard.route
            else Screen.Auth.route
        }

        setContent {
            SignalStrengthTheme {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}
