package com.furkancosgun.expensetrackerapp.presentation.screen.home

import com.furkancosgun.expensetrackerapp.data.model.response.ProjectReportResponse

data class HomeScreenState(
    val createdExpenseReportCount: Int = 0,
    val searchText: String = "",
    val recentExpenseReports: List<ProjectReportResponse> = emptyList()
)