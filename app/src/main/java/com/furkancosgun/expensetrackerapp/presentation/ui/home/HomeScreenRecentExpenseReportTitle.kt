package com.furkancosgun.expensetrackerapp.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding

@Composable
fun HomeScreenRecentExpenseReportTitle(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(UIPadding.MEDIUM.size, UIPadding.NONE.size),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Recent Expense Report", style = MaterialTheme.typography.titleLarge)
        AppTextButton(text = "View All") {

        }
    }
}