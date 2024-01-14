package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Root : Screen("root")

    data object Welcome : Screen("welcome") {
        data object Splash : Screen("splash")
    }

    data object Auth : Screen("auth") {
        data object Login : Screen("login")
        data object Register : Screen("register")
        data object ForgotPassword : Screen("forgotPassword")
        data object VerifyAccount : Screen("verifyAccount?email={email}")
        data object VerifyResetPassword : Screen("verifyResetPassword?email={email}")
        data object ResetPassword : Screen("resetPassword?email={email}&otp={otp}")
    }

    data object App : Screen("app") {
        data object Base : Screen("base")
        data object CreateReport : Screen("createReport")
        data object ChooseExpenseType : Screen("chooseExpenseType") {
            data object Manual : Screen("createManualExpense")
            data object Scan : Screen("crateScanExpense")
            data object Voice : Screen("createVoiceExpense")
        }
    }
}

sealed class BottomNavScreen(val route: String, val icon: ImageVector) {
    data object Home : BottomNavScreen("home", icon = Icons.Default.Home)
    data object Settings : BottomNavScreen("settings", icon = Icons.Default.Settings)
}
