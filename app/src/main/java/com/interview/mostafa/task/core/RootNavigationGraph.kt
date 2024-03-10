package com.interview.mostafa.task.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.interview.mostafa.task.feature_home.presentation.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route
    ) {
        composable(route = ScreenRoutes.Home.route) {
            HomeScreen(navController)
        }
    }
}