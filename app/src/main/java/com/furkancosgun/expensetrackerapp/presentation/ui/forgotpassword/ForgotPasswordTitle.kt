package com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun ForgotPasswordTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = "Enter the email address associated with your account.")
    }
}