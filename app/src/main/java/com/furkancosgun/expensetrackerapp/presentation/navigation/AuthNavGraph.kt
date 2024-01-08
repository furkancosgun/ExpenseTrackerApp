package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword.ForgotPasswordScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.login.LoginScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.register.RegisterScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword.ResetPasswordScreen
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount.VerifyAccountScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Auth.Login.route, route = Screen.Auth.route) {
        composable(route = Screen.Auth.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Auth.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.Auth.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(route = Screen.Auth.VerifyAccount.route) {
            VerifyAccountScreen(navController = navController)
        }
        composable(route = Screen.Auth.ResetPassword.route) {
            ResetPasswordScreen(navController = navController)
        }
    }
}