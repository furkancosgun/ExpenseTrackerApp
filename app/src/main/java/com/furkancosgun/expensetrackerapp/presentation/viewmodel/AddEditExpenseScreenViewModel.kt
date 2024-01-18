package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense.AddEditExpenseScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense.AddEditExpenseScreenState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException

class AddEditExpenseScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val context: Context
) : ViewModel() {
    var state by mutableStateOf(AddEditExpenseScreenState())
        private set

    private val eventChannel = Channel<CreateManualExpenseScreenViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        val expenseId = savedStateHandle.get<String>("id")
    }

    fun onEvent(event: AddEditExpenseScreenEvent) {
        when (event) {
            is AddEditExpenseScreenEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }

            is AddEditExpenseScreenEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }

            is AddEditExpenseScreenEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }

            is AddEditExpenseScreenEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is AddEditExpenseScreenEvent.MerchantNameChanged -> {
                state = state.copy(merchantName = event.merchantName)
            }

            is AddEditExpenseScreenEvent.VatChanged -> {
                state = state.copy(vat = event.vat)
            }

            is AddEditExpenseScreenEvent.Submit -> {

            }

            is AddEditExpenseScreenEvent.IncludeVatChanged -> {
                state = state.copy(includeVat = event.includeVat)
            }

            is AddEditExpenseScreenEvent.CreateCategory -> {
                state = state.copy(isOpenCategoryAlert = !state.isOpenCategoryAlert)
            }

            is AddEditExpenseScreenEvent.UploadImage -> {
                state = state.copy(uploadedImage = event.uri)
                viewModelScope.launch {
                    scanImage()
                }
            }
        }
    }

    private fun scanImage() {
        var image: InputImage? = null
        try {
            image = InputImage.fromFilePath(context, state.uploadedImage!!)
        } catch (e: IOException) {
            e.printStackTrace()
            return
        }
        image.let {
            TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
                .process(it)
                .addOnSuccessListener { text ->

                    val merchantNameRegex = Regex("""^(.*?)(?:\n|$)""", RegexOption.DOT_MATCHES_ALL)
                    val merchantNameMatch = merchantNameRegex.find(text.text)
                    val merchantName = merchantNameMatch?.groupValues?.getOrNull(1) ?: ""

                    val dateRegex = Regex("""TARIH: (\d{2}\.\d{2}\.\d{4})""")
                    val dateMatch = dateRegex.find(text.text)
                    val date = dateMatch?.groupValues?.getOrNull(1) ?: ""

                    val amountRegex = Regex("""TOPLAM\s+(.+?)\s+""", RegexOption.DOT_MATCHES_ALL)
                    val amountMatch = amountRegex.find(text.text)
                    val amount = amountMatch?.groupValues?.getOrNull(1) ?: ""

                    val vatRegex = Regex("""TOPKDV\s+(.+?)\s+""", RegexOption.DOT_MATCHES_ALL)
                    val vatMatch = vatRegex.find(text.text)
                    val vat = vatMatch?.groupValues?.getOrNull(1) ?: ""

                    state = state.copy(
                        merchantName = merchantName,
                        date = date,
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        vat = vat.toDoubleOrNull() ?: 0.0
                    )
                }.addOnFailureListener {
                    Toast.makeText(
                        context, it.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }

    sealed class CreateManualExpenseScreenViewModelEvent {
        data object Success : CreateManualExpenseScreenViewModelEvent()
        data class Error(val error: String) : CreateManualExpenseScreenViewModelEvent()
    }
}