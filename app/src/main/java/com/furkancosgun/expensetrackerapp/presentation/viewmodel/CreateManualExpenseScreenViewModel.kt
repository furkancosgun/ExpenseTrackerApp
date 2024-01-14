package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual.CreateManualExpenseScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual.CreateManualExpenseScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateManualExpenseScreenViewModel : ViewModel() {
    var state by mutableStateOf(CreateManualExpenseScreenState())
        private set

    private val eventChannel = Channel<CreateManualExpenseScreenViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: CreateManualExpenseScreenEvent) {
        when (event) {
            is CreateManualExpenseScreenEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }

            is CreateManualExpenseScreenEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }

            is CreateManualExpenseScreenEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }

            is CreateManualExpenseScreenEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is CreateManualExpenseScreenEvent.MerchantNameChanged -> {
                state = state.copy(merchantName = event.merchantName)
            }

            is CreateManualExpenseScreenEvent.VatChanged -> {
                state = state.copy(vat = event.vat)
            }

            is CreateManualExpenseScreenEvent.Submit -> {

            }

            is CreateManualExpenseScreenEvent.IncludeVatChanged -> {
                state = state.copy(includeVat = event.includeVat)
            }

            is CreateManualExpenseScreenEvent.CreateCategory -> {

            }

            is CreateManualExpenseScreenEvent.UploadImage -> {
                state = state.copy(uploadedImage = event.uri)
            }
        }
    }

    sealed class CreateManualExpenseScreenViewModelEvent {
        data object Success : CreateManualExpenseScreenViewModelEvent()
        data class Error(val error: String) : CreateManualExpenseScreenViewModelEvent()
    }
}