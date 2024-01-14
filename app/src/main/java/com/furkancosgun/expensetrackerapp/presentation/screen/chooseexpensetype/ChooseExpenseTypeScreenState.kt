package com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype

import com.furkancosgun.expensetrackerapp.domain.model.ExpenseType

data class ChooseExpenseTypeScreenState(
    val expenseType: ExpenseType = ExpenseType.Manual
)
