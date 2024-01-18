package com.furkancosgun.expensetrackerapp.presentation.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense.AddEditExpenseScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.createreport.CreateReportScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.main.MainScreen

//Working With Bottom Nav And Other Screens
fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.App.Base.route, route = Screen.App.route) {
        composable(route = Screen.App.Base.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.App.CreateReport.route) {
            CreateReportScreen(navController = navController)
        }
        composable(route = Screen.App.AddEditExpense.route, arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = true
            }
        )) {
            AddEditExpenseScreen(navController = navController)
        }

    }
}
