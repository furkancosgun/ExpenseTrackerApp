package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateReportNameUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.createreport.CreateReportScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.createreport.CreateReportScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateReportViewModel(
    private val validateReportNameUseCase: ValidateReportNameUseCase
) : ViewModel() {
    var state by mutableStateOf(CreateReportScreenState())
        private set
    private val eventChannel = Channel<CreateReportViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: CreateReportScreenEvent) {
        when (event) {
            is CreateReportScreenEvent.ReportNameChanged -> {
                state = state.copy(reportName = event.reportName)
            }

            is CreateReportScreenEvent.Submit -> {
                val validateReportNameUseCaseResult = validateReportNameUseCase(state.reportName)

                state = state.copy(reportNameError = validateReportNameUseCaseResult.errorMessage)

                if (!validateReportNameUseCaseResult.successful) return
                viewModelScope.launch {
                    eventChannel.send(CreateReportViewModelEvent.Success)
                }
            }
        }
    }

    sealed class CreateReportViewModelEvent {
        data object Success : CreateReportViewModelEvent()
        data class Error(val error: String) : CreateReportViewModelEvent()
    }
}