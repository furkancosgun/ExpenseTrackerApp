package com.furkancosgun.expensetrackerapp.presentation.screen.home

import com.furkancosgun.expensetrackerapp.domain.model.RecentExpenseReport

data class HomeScreenState(
    val createdExpenseReportCount: Int = 0,
    val submittedExpenseReportCount: Int = 0,
    val searchText: String = "",
    val recentExpenseReports: List<RecentExpenseReport> = emptyList()
)