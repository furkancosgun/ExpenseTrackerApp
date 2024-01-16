package com.furkancosgun.expensetrackerapp.domain.model

data class RecentExpenseReport(
    val title: String,
    val totalExpenseCount: Int,
    val totalExpenseAmount: Double,
    val lastChangedDate: String,
)
