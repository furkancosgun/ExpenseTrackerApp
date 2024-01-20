package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.CreateCategoryRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitCategoryDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateCategoryUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlertEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlertState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateCategoryViewModel(
    private val validateCategoryUseCase: ValidateCategoryUseCase,
    private val categoryDataSource: RetrofitCategoryDataSource
) : ViewModel() {
    var state by mutableStateOf(CreateCategoryAlertState())
        private set

    private val eventChannel = Channel<CreateCategoryViewModelEvent>()

    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: CreateCategoryAlertEvent) {
        when (event) {
            is CreateCategoryAlertEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }

            is CreateCategoryAlertEvent.Submit -> {
                val validateCategoryUseCaseResult = validateCategoryUseCase(state.category)

                state = state.copy(categoryError = validateCategoryUseCaseResult.errorMessage)

                if (!validateCategoryUseCaseResult.successful) return

                viewModelScope.launch {
                    saveCategory()
                }
            }
        }
    }

    private suspend fun saveCategory() {
        val request = CreateCategoryRequest(name = state.category)
        httpRequestHandler(request = {
            categoryDataSource.createCategory(request)
        }, onSuccess = {
            viewModelScope.launch {
                eventChannel.send(CreateCategoryViewModelEvent.Success)
            }
        }, onError = {
            viewModelScope.launch {
                eventChannel.send(CreateCategoryViewModelEvent.Error(it))
            }
        })
    }

    sealed class CreateCategoryViewModelEvent {
        data object Success : CreateCategoryViewModelEvent()
        data class Error(val error: String) : CreateCategoryViewModelEvent()
    }
}