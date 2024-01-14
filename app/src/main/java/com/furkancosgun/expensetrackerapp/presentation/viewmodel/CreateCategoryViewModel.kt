package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateCategoryUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlertEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlertState

class CreateCategoryViewModel(
    private val validateCategoryUseCase: ValidateCategoryUseCase
) : ViewModel() {
    var state by mutableStateOf(CreateCategoryAlertState())
    fun onEvent(event: CreateCategoryAlertEvent) {
        when (event) {
            is CreateCategoryAlertEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }

            is CreateCategoryAlertEvent.Submit -> {
                val validateCategoryUseCaseResult = validateCategoryUseCase(state.category)

                state = state.copy(categoryError = validateCategoryUseCaseResult.errorMessage)

                if (!validateCategoryUseCaseResult.successful) return

                
            }
        }
    }
}