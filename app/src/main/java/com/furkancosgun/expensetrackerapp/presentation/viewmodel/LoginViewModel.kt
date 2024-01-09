package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.login.LoginScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.login.LoginScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
    private val eventChannel = Channel<LoginViewModelEvent>()
    val event = eventChannel.receiveAsFlow()
    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginScreenEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginScreenEvent.Submit -> {
                val emailUseCaseResult = validateEmailUseCase(state.email)
                val passwordUseCaseResult = validatePasswordUseCase(state.password)

                state = state.copy(
                    emailError = emailUseCaseResult.errorMessage,
                    passwordError = passwordUseCaseResult.errorMessage
                )

                val hasError = mutableListOf(emailUseCaseResult, passwordUseCaseResult).any {
                    !it.successful
                }

                if (hasError) return

                viewModelScope.launch {
                    eventChannel.send(LoginViewModelEvent.Success)
                }
            }
        }
    }

    sealed class LoginViewModelEvent {
        data object Success : LoginViewModelEvent()
        data class Error(val error: String) : LoginViewModelEvent()
    }
}