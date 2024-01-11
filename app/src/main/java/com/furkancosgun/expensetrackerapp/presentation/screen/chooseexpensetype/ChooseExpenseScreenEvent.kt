package com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype

sealed class ChooseExpenseScreenEvent {
    data class ExpenseTypeChanged(val expenseType: ExpenseType) : ChooseExpenseScreenEvent()
}
