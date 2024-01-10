package com.furkancosgun.expensetrackerapp.presentation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenReportAndExpenseStatus
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenReportNotFoundContent
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size)
    ) {
        HomeScreenReportAndExpenseStatus()
        if (1 == 1) {
            HomeScreenReportNotFoundContent(onClick = {
                navController.navigate(Screen.App.CreateReport.route)
            })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreen_Preview() {
    ExpenseTrackerTheme {
        HomeScreen(navController = rememberNavController())
    }
}