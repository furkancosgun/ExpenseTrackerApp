package com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype

import androidx.annotation.StringRes
import com.furkancosgun.expensetrackerapp.R

sealed class ExpenseType(@StringRes val title: Int) {
    data object Manual : ExpenseType(R.string.manual_input)
    data object Scan : ExpenseType(R.string.scan_receipt)
    data object Voice : ExpenseType(R.string.voice_recognition)
}
