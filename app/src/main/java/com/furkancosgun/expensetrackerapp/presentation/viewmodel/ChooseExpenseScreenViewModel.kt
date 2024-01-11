package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype.ChooseExpenseScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype.ChooseExpenseScreenState
import com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype.ExpenseType

class ChooseExpenseScreenViewModel : ViewModel() {

    val selectableExpenseTypes =
        mutableListOf(ExpenseType.Manual, ExpenseType.Scan, ExpenseType.Voice)
    var state by mutableStateOf(ChooseExpenseScreenState())
        private set

    fun onEvent(event: ChooseExpenseScreenEvent) {
        when (event) {
            is ChooseExpenseScreenEvent.ExpenseTypeChanged -> {
                state = state.copy(expenseType = event.expenseType)
            }
        }
    }
}