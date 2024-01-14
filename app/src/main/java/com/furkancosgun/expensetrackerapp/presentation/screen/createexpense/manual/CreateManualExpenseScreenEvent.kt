package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

import android.net.Uri

sealed class CreateManualExpenseScreenEvent {
    data class MerchantNameChanged(val merchantName: String) : CreateManualExpenseScreenEvent()
    data class AmountChanged(val amount: Double) : CreateManualExpenseScreenEvent()
    data class DateChanged(val date: String) : CreateManualExpenseScreenEvent()
    data class DescriptionChanged(val description: String) : CreateManualExpenseScreenEvent()
    data class CategoryChanged(val category: String) : CreateManualExpenseScreenEvent()
    data class VatChanged(val vat: Double) : CreateManualExpenseScreenEvent()
    data class IncludeVatChanged(val includeVat: Boolean) : CreateManualExpenseScreenEvent()
    data object Submit : CreateManualExpenseScreenEvent()
    data object CreateCategory : CreateManualExpenseScreenEvent()
    data class UploadImage(val uri: Uri) : CreateManualExpenseScreenEvent()
}
