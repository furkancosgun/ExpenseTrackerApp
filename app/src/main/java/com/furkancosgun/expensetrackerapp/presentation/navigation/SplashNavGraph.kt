package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.screen.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Welcome.Splash.route, route = Screen.Welcome.route) {
        composable(route = Screen.Welcome.Splash.route) {
            SplashScreen(navController = navController)
        }
    }
}