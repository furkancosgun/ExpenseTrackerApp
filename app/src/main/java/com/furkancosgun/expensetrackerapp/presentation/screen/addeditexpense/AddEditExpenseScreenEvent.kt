package com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense

import android.net.Uri

sealed class AddEditExpenseScreenEvent {
    data class MerchantNameChanged(val merchantName: String) : AddEditExpenseScreenEvent()
    data class AmountChanged(val amount: Double) : AddEditExpenseScreenEvent()
    data class DateChanged(val date: String) : AddEditExpenseScreenEvent()
    data class DescriptionChanged(val description: String) : AddEditExpenseScreenEvent()
    data class CategoryChanged(val category: String) : AddEditExpenseScreenEvent()
    data class VatChanged(val vat: Double) : AddEditExpenseScreenEvent()
    data class IncludeVatChanged(val includeVat: Boolean) : AddEditExpenseScreenEvent()
    data object Submit : AddEditExpenseScreenEvent()
    data object CreateCategory : AddEditExpenseScreenEvent()
    data class UploadImage(val uri: Uri) : AddEditExpenseScreenEvent()
}
