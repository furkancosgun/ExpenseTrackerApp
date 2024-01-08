package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBarController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        route = Screen.Root.route
    ) {
        splashNavGraph(navController)
        authNavGraph(navController)
        bottomNavGraph(navBarController)
    }
}

