package com.furkancosgun.expensetrackerapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Root : Screen("root")

    data object Welcome : Screen("welcome") {
        data object Splash : Screen("splash")
    }

    data object Auth : Screen("auth") {
        data object Login : Screen("login")
        data object Register : Screen("register")
        data object ForgotPassword : Screen("forgotPassword")
        data object VerifyAccount : Screen("verifyAccount?email={email}&next={next}")
        data object ResetPassword : Screen("resetPassword?email={email}")
    }

    data object App : Screen("app") {
        data object Base : Screen("base")
    }
}
