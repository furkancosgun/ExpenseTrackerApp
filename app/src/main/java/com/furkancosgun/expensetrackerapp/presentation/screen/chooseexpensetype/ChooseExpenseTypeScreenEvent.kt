package com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype

import com.furkancosgun.expensetrackerapp.domain.model.ExpenseType

sealed class ChooseExpenseTypeScreenEvent {
    data class ExpenseTypeChanged(val expenseType: ExpenseType) :
        ChooseExpenseTypeScreenEvent()
}
