package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword.ForgotPasswordTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.otp.OtpTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.verifyaccount.VerifyAccountScreenTitle

@Composable
fun VerifyAccountScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center
    ) {
        VerifyAccountScreenTitle(email = "furkan51cosgun@gmail.com")
        Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
        OtpTextField(
            modifier = Modifier.fillMaxWidth(),
            otpText = "123",
            onOtpTextChange = {})
        Spacer(modifier = Modifier.height(UISpacing.MEDIUM.size))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Verify"
        ) {
            navController.navigate(Screen.Auth.ResetPassword.route)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VerifyAccountScreen_Preview() {
    ExpenseTrackerTheme {
        VerifyAccountScreen(navController = rememberNavController())
    }
}
