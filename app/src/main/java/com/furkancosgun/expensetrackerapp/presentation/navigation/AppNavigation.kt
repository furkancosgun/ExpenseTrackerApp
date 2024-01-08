package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.screen.bottomnavigation.BottomNavigationScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.splash.SplashScreen

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
fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Welcome.Splash.route, route = Screen.Welcome.route) {
        composable(route = Screen.Welcome.Splash.route) {
            SplashScreen(navController = navController)
        }
    }
}
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Auth.Login.route, route = Screen.Auth.route) {
        composable(route = Screen.Auth.Login.route) {
            //LoginScreen(navController = navController)
        }
        composable(route = Screen.Auth.Register.route) {
            //RegisterScreen(navController = navController)
        }
        composable(route = Screen.Auth.ForgotPassword.route) {
            //ForgotPasswordScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.bottomNavGraph(navBarController: NavHostController) {
    navigation(startDestination = Screen.App.Base.route, route = Screen.App.route) {
        composable(route = Screen.App.Base.route) {
            BottomNavigationScreen(navController = navBarController)
        }
    }
}
@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Home.route
    ) {
        composable(route = BottomNavScreen.Home.route) {
            //HomeScreen(navController = navController)
        }
    }
}