package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

data class CreateManualExpenseScreenState(
    val merchantName: String = "",
    val merchantNameError: String? = null,
    val amount: Double = 0.0,
    val amountError: String? = null,
    val date: String = "",
    val dateError: String? = null,
    val description: String = "",
    val category: String = "",
    val vat: Double = 0.0
)
