package com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun ForgotPasswordTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = stringResource(R.string.enter_your_email_for_the_verification_process_we_will_send_6_digits_code_to_your_email))
    }
}