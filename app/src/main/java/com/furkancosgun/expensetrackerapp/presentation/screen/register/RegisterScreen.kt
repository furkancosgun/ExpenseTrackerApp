package com.furkancosgun.expensetrackerapp.presentation.screen.register

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedPasswordField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.register.RegisterScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun RegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center,
    ) {
        RegisterScreenTitle()

        Spacer(modifier = Modifier.height(UIPadding.LARGE.size))

        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            label = "First Name",
            onTextChanged = {},
            icon = Icons.Default.Person
        )
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            label = "Last Name",
            onTextChanged = {},
            icon = Icons.Default.Person
        )
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
        AppOutlinedPasswordField(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            label = "Password Again",
            onTextChanged = {},
            icon = Icons.Default.Password
        )
        AppButton(modifier = Modifier.fillMaxWidth(), text = "Register") {

        }

        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Already have a account?",
            textAlign = TextAlign.Center
        ) {
            navController.popBackStack()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegisterScreen_Preview() {
    ExpenseTrackerTheme {
        RegisterScreen(rememberNavController())
    }
}