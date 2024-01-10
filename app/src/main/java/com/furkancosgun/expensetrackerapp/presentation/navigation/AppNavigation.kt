package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.App.route,
        route = Screen.Root.route
    ) {
        splashNavGraph(navController)
        authNavGraph(navController)
        appNavGraph(navController)
    }
}

