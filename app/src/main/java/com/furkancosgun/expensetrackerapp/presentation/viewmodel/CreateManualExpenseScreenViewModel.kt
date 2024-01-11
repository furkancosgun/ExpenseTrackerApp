package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual.CreateManualExpenseEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual.CreateManualExpenseScreenState

class CreateManualExpenseScreenViewModel : ViewModel() {
    var state by mutableStateOf(CreateManualExpenseScreenState())
        private set


    fun onEvent(event: CreateManualExpenseEvent) {
        when (event) {
            is CreateManualExpenseEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }

            is CreateManualExpenseEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }

            is CreateManualExpenseEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }

            is CreateManualExpenseEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is CreateManualExpenseEvent.MerchantNameChanged -> {
                state = state.copy(merchantName = event.merchantName)
            }

            is CreateManualExpenseEvent.VatChanged -> {
                state = state.copy(vat = event.vat)
            }

            is CreateManualExpenseEvent.Submit -> {

            }

            is CreateManualExpenseEvent.IncludeVatChanged -> {
                state = state.copy(includeVat = event.includeVat)
            }

            is CreateManualExpenseEvent.CreateCategory -> TODO()
            is CreateManualExpenseEvent.UploadImage -> TODO()
        }
    }
}