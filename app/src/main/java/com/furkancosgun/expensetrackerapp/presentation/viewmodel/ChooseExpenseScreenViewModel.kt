package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype.ChooseExpenseTypeScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype.ChooseExpenseTypeScreenState
import com.furkancosgun.expensetrackerapp.domain.model.ExpenseType

class ChooseExpenseScreenViewModel : ViewModel() {

    val selectableExpenseTypes =
        mutableListOf(ExpenseType.Manual, ExpenseType.Scan, ExpenseType.Voice)
    var state by mutableStateOf(ChooseExpenseTypeScreenState())
        private set

    fun onEvent(event: ChooseExpenseTypeScreenEvent) {
        when (event) {
            is ChooseExpenseTypeScreenEvent.ExpenseTypeChanged -> {
                state = state.copy(expenseType = event.expenseType)
            }
        }
    }
}