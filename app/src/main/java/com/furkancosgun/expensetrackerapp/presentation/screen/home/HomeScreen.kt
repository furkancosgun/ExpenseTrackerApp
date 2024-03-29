package com.furkancosgun.expensetrackerapp.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenProjectItem
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenRecentExpenseReportTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenReportAndExpenseStatus
import com.furkancosgun.expensetrackerapp.presentation.ui.home.HomeScreenReportNotFoundContent
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = koinViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size)
    ) {
        HomeScreenReportAndExpenseStatus(expenseReportCount = viewModel.state.createdExpenseReportCount)
        if (viewModel.state.recentExpenseReports.isEmpty()) {
            HomeScreenReportNotFoundContent()
        } else {
            HomeScreenRecentExpenseReportTitle()
            AppOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIPadding.MEDIUM.size),
                label = stringResource(R.string.search),
                text = viewModel.state.searchText,
                onTextChanged = {
                    viewModel.onEvent(HomeScreenEvent.SearchTextChanged(it))
                },
                icon = Icons.Default.Search
            )
            LazyColumn {
                items(viewModel.state.recentExpenseReports) {
                    HomeScreenProjectItem(modifier = Modifier,it)
                }
            }
        }
    }
}
