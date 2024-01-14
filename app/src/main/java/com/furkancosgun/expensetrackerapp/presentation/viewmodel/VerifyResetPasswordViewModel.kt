package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.VerifyAccountRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateOtpCodeUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyresetpassword.VerifyResetPasswordScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyresetpassword.VerifyResetPasswordScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyResetPasswordViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validateOtpCodeUseCase: ValidateOtpCodeUseCase,
    private val authDataSource: RetrofitAuthDataSource,
) : ViewModel() {
    var state by mutableStateOf(VerifyResetPasswordScreenState())
        private set
    private val eventChannel = Channel<VerifyResetPasswordViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: VerifyResetPasswordScreenEvent) {
        when (event) {
            is VerifyResetPasswordScreenEvent.OtpCodeChanged -> {
                state = state.copy(otpCode = event.otpCode)
            }

            is VerifyResetPasswordScreenEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val otpCodeUseCaseResult = validateOtpCodeUseCase(state.otpCode)

        state = state.copy(otpCodeError = otpCodeUseCaseResult.errorMessage)

        if (!otpCodeUseCaseResult.successful) return

        viewModelScope.launch {
            verifyAccountRequest()
        }
    }

    private suspend fun verifyAccountRequest() {
        val email = savedStateHandle.get<String>("email") ?: return
        httpRequestHandler(request = {
            authDataSource.verifyAccount(
                VerifyAccountRequest(
                    email = email,
                    otp = state.otpCode,
                )
            )
        }, onSuccess = { _ ->
            sendEvent(VerifyResetPasswordViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(VerifyResetPasswordViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: VerifyResetPasswordViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    sealed class VerifyResetPasswordViewModelEvent {
        data object Success : VerifyResetPasswordViewModelEvent()
        data class Error(val error: String) : VerifyResetPasswordViewModelEvent()
    }
}