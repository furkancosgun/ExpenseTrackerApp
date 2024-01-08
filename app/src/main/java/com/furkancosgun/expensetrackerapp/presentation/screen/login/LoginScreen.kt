package com.furkancosgun.expensetrackerapp.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedPasswordField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.login.LoginScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LoginScreenTitle()
        Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            label = "Email",
            onTextChanged = {},
            icon = Icons.Default.Email
        )
        AppOutlinedPasswordField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            label = "Password",
            onTextChanged = {},
            icon = Icons.Default.Password
        )
        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.forgot_your_password)
        ) {
            navController.navigate(Screen.Auth.ForgotPassword.route)
        }
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login)
        ) {
        }

        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.don_t_have_a_account),
            textAlign = TextAlign.Center
        ) {
            navController.navigate(Screen.Auth.Register.route)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreen_Preview() {
    ExpenseTrackerTheme {
        LoginScreen(navController = rememberNavController())
    }
}