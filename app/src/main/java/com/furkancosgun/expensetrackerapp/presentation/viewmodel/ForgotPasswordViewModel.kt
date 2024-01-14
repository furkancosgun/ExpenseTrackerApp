package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.ForgotPasswordRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword.ForgotPasswordScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword.ForgotPasswordScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val authDataSource: RetrofitAuthDataSource
) : ViewModel() {
    var state by mutableStateOf(ForgotPasswordScreenState())
        private set
    private val eventChannel = Channel<ForgotPasswordViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: ForgotPasswordScreenEvent) {
        when (event) {
            is ForgotPasswordScreenEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is ForgotPasswordScreenEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailUseCaseResult = validateEmailUseCase(state.email)

        state = state.copy(emailError = emailUseCaseResult.errorMessage)

        if (!emailUseCaseResult.successful) return

        viewModelScope.launch {
            verifyAccountRequest()
        }
    }

    private suspend fun verifyAccountRequest() {
        httpRequestHandler(request = {
            authDataSource.forgotPassword(
                ForgotPasswordRequest(state.email)
            )
        }, onSuccess = { _ ->
            sendEvent(ForgotPasswordViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(ForgotPasswordViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: ForgotPasswordViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    sealed class ForgotPasswordViewModelEvent {
        data object Success : ForgotPasswordViewModelEvent()
        data class Error(val error: String) : ForgotPasswordViewModelEvent()
    }
}