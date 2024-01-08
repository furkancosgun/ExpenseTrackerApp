package com.furkancosgun.expensetrackerapp.presentation.ui.verifyaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun VerifyAccountScreenTitle(modifier: Modifier = Modifier, email: String) {
    Column(modifier = modifier) {
        Text(
            text = "Verification Code",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = stringResource(R.string.we_sent_to_the_e_mail))
        Text(text = email, style = MaterialTheme.typography.titleMedium)
    }
}