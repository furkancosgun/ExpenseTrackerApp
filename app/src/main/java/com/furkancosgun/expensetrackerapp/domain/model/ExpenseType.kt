package com.furkancosgun.expensetrackerapp.domain.model

import androidx.annotation.StringRes
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen

sealed class ExpenseType(@StringRes val title: Int, val screen: Screen) {
    data object Manual :
        ExpenseType(R.string.manual_input, Screen.App.ChooseExpenseType.Manual)

    data object Scan : ExpenseType(R.string.scan_receipt, Screen.App.ChooseExpenseType.Scan)
    data object Voice : ExpenseType(R.string.voice_recognition, Screen.App.ChooseExpenseType.Voice)
}
