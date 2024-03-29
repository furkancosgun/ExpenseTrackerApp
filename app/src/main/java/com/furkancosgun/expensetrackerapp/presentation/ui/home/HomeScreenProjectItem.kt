package com.furkancosgun.expensetrackerapp.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.data.model.response.ProjectReportResponse
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun HomeScreenProjectItem(modifier: Modifier = Modifier, expenseReport: ProjectReportResponse) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(UIPadding.MEDIUM.size),
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
        border = BorderStroke(1.dp, Color.Gray),
    ) {
        Column(
            modifier = Modifier.padding(UIPadding.MEDIUM.size),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(UIPadding.MEDIUM.size),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = expenseReport.projectName ?: "",
                    style = MaterialTheme.typography.titleMedium
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = (expenseReport.totalAmount ?: 0.0).toString())
                AppTextButton(text = "View More") {

                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(UIPadding.MEDIUM.size)
            ) {
                Text(text = expenseReport.createdAt ?: "")
                Text(text = "|")
                Text(text = stringResource(R.string.s_expense, expenseReport.totalExpenses ?: 0))
            }
        }
    }
}
