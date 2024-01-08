package com.furkancosgun.expensetrackerapp.presentation.ui.resetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun ResetPasswordScreenTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Reset Password",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = "Set the new password for your account so you can login and access all the features.")
    }
}