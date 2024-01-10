package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword.ForgotPasswordScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword.ForgotPasswordScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase
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
                val emailUseCaseResult = validateEmailUseCase(state.email)

                state = state.copy(emailError = emailUseCaseResult.errorMessage)

                if (!emailUseCaseResult.successful) return

                viewModelScope.launch {
                    eventChannel.send(ForgotPasswordViewModelEvent.Success)
                }
            }
        }
    }

    sealed class ForgotPasswordViewModelEvent {
        data object Success : ForgotPasswordViewModelEvent()
        data class Error(val error: String) : ForgotPasswordViewModelEvent()
    }
}