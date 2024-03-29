package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.ResetPasswordRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword.ResetPasswordScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword.ResetPasswordScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val authDataSource: RetrofitAuthDataSource
) : ViewModel() {
    var state by mutableStateOf(ResetPasswordScreenState())
        private set
    private val eventChannel = Channel<ResetPasswordViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: ResetPasswordScreenEvent) {
        when (event) {
            is ResetPasswordScreenEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is ResetPasswordScreenEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is ResetPasswordScreenEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val validatePasswordUseCaseResult = validatePasswordUseCase(state.password)
        val validateRepeatedPasswordUseCaseResult =
            validateRepeatedPasswordUseCase(state.password, state.repeatedPassword)

        state = state.copy(
            passwordError = validatePasswordUseCaseResult.errorMessage,
            repeatedPasswordError = validateRepeatedPasswordUseCaseResult.errorMessage
        )

        val hasError = mutableListOf(
            validatePasswordUseCaseResult,
            validateRepeatedPasswordUseCaseResult
        ).any {
            !it.successful
        }

        if (hasError) return

        viewModelScope.launch {
            sendResetPasswordRequest()
        }
    }

    private suspend fun sendResetPasswordRequest() {
        val email = savedStateHandle.get<String>("email") ?: ""
        val otp = savedStateHandle.get<String>("otp") ?: ""
        httpRequestHandler(request = {
            authDataSource.resetPassword(
                ResetPasswordRequest(
                    email = email,
                    otp = otp,
                    password = state.password,
                )
            )
        }, onSuccess = { _ ->
            sendEvent(ResetPasswordViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(ResetPasswordViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: ResetPasswordViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    sealed class ResetPasswordViewModelEvent {
        data object Success : ResetPasswordViewModelEvent()
        data class Error(val error: String) : ResetPasswordViewModelEvent()
    }
}