package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.screen.bottomnavigation.BottomNavigationScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.CreateExpenseScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.createreport.CreateReportScreen

fun NavGraphBuilder.appNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.App.Base.route, route = Screen.App.route) {
        composable(route = Screen.App.Base.route) {
            BottomNavigationScreen(navController = navController)
        }
        composable(route = Screen.App.CreateReport.route) {
            CreateReportScreen(navController = navController)
        }
        composable(route = Screen.App.CreateExpense.route) {
            CreateExpenseScreen(navController = navController)
        }
    }
}
