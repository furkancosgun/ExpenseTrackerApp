package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

sealed class CreateManualExpenseEvent {
    data class MerchantNameChanged(val merchantName: String) : CreateManualExpenseEvent()
    data class AmountChanged(val amount: Double) : CreateManualExpenseEvent()
    data class DateChanged(val date: String) : CreateManualExpenseEvent()
    data class DescriptionChanged(val description: String) : CreateManualExpenseEvent()
    data class CategoryChanged(val category: String) : CreateManualExpenseEvent()
    data class VatChanged(val vat: Double) : CreateManualExpenseEvent()
    data object Submit : CreateManualExpenseEvent()
}
