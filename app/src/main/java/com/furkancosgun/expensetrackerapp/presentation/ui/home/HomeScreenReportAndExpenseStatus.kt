package com.furkancosgun.expensetrackerapp.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding

@Composable
fun HomeScreenReportAndExpenseStatus(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxHeight(0.15f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(UIPadding.MEDIUM.size),
            colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
            border = BorderStroke(0.5.dp, Color.Gray)
        ) {
            Column(modifier = Modifier.padding(UIPadding.MEDIUM.size)) {
                Text(text = "0", style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(R.string.expense_report_created))
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(UIPadding.MEDIUM.size),
            colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
            border = BorderStroke(0.5.dp, Color.Gray)
        ) {
            Column(modifier = Modifier.padding(UIPadding.MEDIUM.size)) {
                Text(text = "0", style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(R.string.expense_report_submitted))
            }
        }
    }
}