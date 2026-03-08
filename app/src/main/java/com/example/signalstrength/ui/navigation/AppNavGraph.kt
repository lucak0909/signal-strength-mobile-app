package com.example.signalstrength.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.signalstrength.ui.screens.auth.AuthScreen
import com.example.signalstrength.ui.screens.dashboard.DashboardScreen
import com.example.signalstrength.ui.screens.detail.ReadingDetailScreen
import com.example.signalstrength.ui.screens.history.HistoryScreen
import com.example.signalstrength.ui.screens.roomdetail.RoomDetailScreen
import com.example.signalstrength.ui.screens.rooms.RoomsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Auth.route) {
            AuthScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        // Remove Auth from back stack — Back on Dashboard exits the app
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToHistory = { navController.navigate(Screen.History.route) },
                onNavigateToRooms   = { navController.navigate(Screen.Rooms.route) },
                onLogout = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onNavigateToDetail = { readingId ->
                    navController.navigate(Screen.ReadingDetail.createRoute(readingId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Rooms.route) {
            RoomsScreen(
                onNavigateToDetail = { roomId ->
                    navController.navigate(Screen.RoomDetail.createRoute(roomId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.RoomDetail.route,
            arguments = listOf(navArgument("roomId") { type = NavType.LongType })
        ) {
            RoomDetailScreen(
                onNavigateToReadingDetail = { readingId ->
                    navController.navigate(Screen.ReadingDetail.createRoute(readingId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.ReadingDetail.route,
            arguments = listOf(navArgument("readingId") { type = NavType.LongType })
        ) {
            ReadingDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
