package com.furkancosgun.expensetrackerapp.presentation.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun LoginScreenTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = stringResource(R.string.login_with_your_email_and_password))
    }
}