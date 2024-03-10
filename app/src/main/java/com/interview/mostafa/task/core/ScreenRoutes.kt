package com.interview.mostafa.task.core

sealed class ScreenRoutes(val route: String) {
    object Home : ScreenRoutes(route = "Home")
    object Details : ScreenRoutes(route = "Details")
}