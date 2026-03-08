package com.example.signalstrength.ui.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Dashboard : Screen("dashboard")
    object History : Screen("history")
    object Rooms : Screen("rooms")
    object RoomDetail : Screen("room/{roomId}") {
        fun createRoute(roomId: Long) = "room/$roomId"
    }
    object ReadingDetail : Screen("detail/{readingId}") {
        fun createRoute(readingId: Long) = "detail/$readingId"
    }
}
