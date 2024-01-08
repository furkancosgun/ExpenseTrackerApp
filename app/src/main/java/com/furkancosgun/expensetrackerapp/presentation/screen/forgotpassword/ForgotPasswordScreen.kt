package com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword

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
import com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword.ForgotPasswordTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center
    ) {
        ForgotPasswordTitle()
        Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            text = "",
            onTextChanged = {},
            icon = Icons.Default.Email
        )
        AppButton(modifier = Modifier.fillMaxWidth(), text = "Send") {
            navController.navigate(Screen.Auth.VerifyAccount.route)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreen_Preview() {
    ExpenseTrackerTheme {
        ForgotPasswordScreen(rememberNavController())
    }
}