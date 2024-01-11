package com.furkancosgun.expensetrackerapp.domain.model

data class RecentExpenseReport(
    val title: String,
    val totalExpenseCount: Int,
    val totalExpenseAmount: Double,
    val currency: String,
    val lastChangedDate: String,
    val isSubmitted: Boolean
) {
    fun toStringCurrencyWithAmount(): String {
        return "${currency}${totalExpenseAmount}"
    }
}
