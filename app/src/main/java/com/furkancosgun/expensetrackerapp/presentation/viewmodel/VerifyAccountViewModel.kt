package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateOtpCodeUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount.VerifyAccountScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount.VerifyAccountScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyAccountViewModel(
    private val validateOtpCodeUseCase: ValidateOtpCodeUseCase
) : ViewModel() {
    var state by mutableStateOf(VerifyAccountScreenState())
    private val eventChannel = Channel<VerifyAccountViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: VerifyAccountScreenEvent) {
        when (event) {
            is VerifyAccountScreenEvent.OtpCodeChanged -> {
                state = state.copy(otpCode = event.otpCode)
            }

            is VerifyAccountScreenEvent.Submit -> {
                val otpCodeUseCaseResult = validateOtpCodeUseCase(state.otpCode)

                state = state.copy(otpCodeError = otpCodeUseCaseResult.errorMessage)

                if (!otpCodeUseCaseResult.successful) return
                viewModelScope.launch {
                    eventChannel.send(VerifyAccountViewModelEvent.Success)
                }
            }
        }
    }

    sealed class VerifyAccountViewModelEvent {
        data object Success : VerifyAccountViewModelEvent()
        data class Error(val error: String) : VerifyAccountViewModelEvent()
    }
}