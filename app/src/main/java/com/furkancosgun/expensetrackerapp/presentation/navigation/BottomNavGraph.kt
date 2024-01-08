package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.screen.bottomnavigation.BottomNavigationScreen

fun NavGraphBuilder.bottomNavGraph(navBarController: NavHostController) {
    navigation(startDestination = Screen.App.Base.route, route = Screen.App.route) {
        composable(route = Screen.App.Base.route) {
            BottomNavigationScreen(navController = navBarController)
        }
    }
}
