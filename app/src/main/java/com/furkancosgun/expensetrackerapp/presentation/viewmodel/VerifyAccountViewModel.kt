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
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount.VerifyAccountScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount.VerifyAccountScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyAccountViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validateOtpCodeUseCase: ValidateOtpCodeUseCase,
    private val authDataSource: RetrofitAuthDataSource,
) : ViewModel() {
    var state by mutableStateOf(VerifyAccountScreenState())
        private set
    private val eventChannel = Channel<VerifyAccountViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: VerifyAccountScreenEvent) {
        when (event) {
            is VerifyAccountScreenEvent.OtpCodeChanged -> {
                state = state.copy(otpCode = event.otpCode)
            }

            is VerifyAccountScreenEvent.Submit -> {
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
                    otp = state.otpCode
                )
            )
        }, onSuccess = { _ ->
            sendEvent(VerifyAccountViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(VerifyAccountViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: VerifyAccountViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    sealed class VerifyAccountViewModelEvent {
        data object Success : VerifyAccountViewModelEvent()
        data class Error(val error: String) : VerifyAccountViewModelEvent()
    }
}