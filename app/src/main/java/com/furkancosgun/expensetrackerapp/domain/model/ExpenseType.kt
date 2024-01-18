package com.furkancosgun.expensetrackerapp.domain.model

import androidx.annotation.StringRes
import com.furkancosgun.expensetrackerapp.R

sealed class ExpenseType(@StringRes val title: Int, val type: String) {
    data object Manual :
        ExpenseType(R.string.manual_input, "manual")

    data object Scan : ExpenseType(R.string.scan_receipt, "scan")
    data object Voice : ExpenseType(R.string.voice_recognition, "voice")
}
