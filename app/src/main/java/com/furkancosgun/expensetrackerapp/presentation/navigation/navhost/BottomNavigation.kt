package com.furkancosgun.expensetrackerapp.presentation.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.home.HomeScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.settings.SettingsScreen


@Composable
fun BottomNavigation(navBarHostController: NavHostController, navController: NavController) {
    NavHost(
        navController = navBarHostController,
        startDestination = BottomNavScreen.Home.route
    ) {
        composable(route = BottomNavScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomNavScreen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}