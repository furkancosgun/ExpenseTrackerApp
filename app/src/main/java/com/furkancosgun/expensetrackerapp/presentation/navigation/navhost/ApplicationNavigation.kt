package com.furkancosgun.expensetrackerapp.presentation.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.navigation.navgraph.authNavGraph
import com.furkancosgun.expensetrackerapp.presentation.navigation.navgraph.mainNavGraph
import com.furkancosgun.expensetrackerapp.presentation.navigation.navgraph.splashNavGraph

@Composable
fun ApplicationNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        route = Screen.Root.route
    ) {
        splashNavGraph(navController)
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}

