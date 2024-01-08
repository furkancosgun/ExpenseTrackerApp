package com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
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
import com.furkancosgun.expensetrackerapp.presentation.ui.resetpassword.ResetPasswordScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun ResetPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center
    ) {
        ResetPasswordScreenTitle()
        Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onTextChanged = {},
            label = "Password",
            icon = Icons.Default.Password
        )
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onTextChanged = {},
            label = "Again Password",
            icon = Icons.Default.Password
        )
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Reset Password"
        ) {
            navController.navigate(Screen.Auth.Login.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ResetPasswordScreen_Preview() {
    ExpenseTrackerTheme {
        ResetPasswordScreen(navController = rememberNavController())
    }
}