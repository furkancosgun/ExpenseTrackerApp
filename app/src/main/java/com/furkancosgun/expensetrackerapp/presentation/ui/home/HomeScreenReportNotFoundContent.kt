package com.furkancosgun.expensetrackerapp.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing

@Composable
fun HomeScreenReportNotFoundContent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_expense_report_found),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(UISpacing.MEDIUM.size))
        Text(text = stringResource(R.string.create_an_expense_report_to_get_started))

        Spacer(modifier = Modifier.height(UISpacing.EXTRA_LARGE.size))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.create_expense_report),
            onClick = onClick
        )
    }
}