package com.interview.mostafa.task.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interview.mostafa.task.core.utli.Constants
import com.interview.mostafa.task.feature_details.DetailsScreen
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

        composable(
            ScreenRoutes.Details.route.plus(
                "?${Constants.TITLE}={${Constants.TITLE}}" +
                        "&${Constants.DESCRIPTION}={${Constants.DESCRIPTION}}" +
                        "&${Constants.IMAGE_URL}={${Constants.IMAGE_URL}}" +
                        "&${Constants.INGREDIENTS}={${Constants.INGREDIENTS}}"
            ),
            arguments = listOf(
                navArgument(name = Constants.TITLE) {
                    type = NavType.StringType
                },
                navArgument(name = Constants.DESCRIPTION) {
                    type = NavType.StringType
                },
                navArgument(name = Constants.IMAGE_URL) {
                    type = NavType.StringType
                },
                navArgument(name = Constants.INGREDIENTS) {
                    type = NavType.StringType
                })
        ) {
            DetailsScreen(navController = navController)
        }
    }
}