package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateFirstNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateLastNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.register.RegisterScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.register.RegisterScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase
) : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())

    private val eventChannel = Channel<RegisterViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {

            is RegisterScreenEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }

            is RegisterScreenEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }

            is RegisterScreenEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is RegisterScreenEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegisterScreenEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegisterScreenEvent.Submit -> {
                val firstNameUseCaseResult = validateFirstNameUseCase(state.firstName)
                val lastNameUseCaseResult = validateLastNameUseCase(state.lastName)
                val emailUseCaseResult = validateEmailUseCase(state.email)
                val passwordUseCaseResult = validatePasswordUseCase(state.password)
                val repeatedPasswordUseCaseResult =
                    validateRepeatedPasswordUseCase(state.password, state.repeatedPassword)

                state = state.copy(
                    firstNameError = firstNameUseCaseResult.errorMessage,
                    lastNameError = lastNameUseCaseResult.errorMessage,
                    emailError = emailUseCaseResult.errorMessage,
                    passwordError = passwordUseCaseResult.errorMessage,
                    repeatedPasswordError = repeatedPasswordUseCaseResult.errorMessage
                )

                val hasError = mutableListOf(
                    firstNameUseCaseResult,
                    lastNameUseCaseResult,
                    emailUseCaseResult,
                    passwordUseCaseResult,
                    repeatedPasswordUseCaseResult
                ).any {
                    !it.successful
                }

                if (hasError) return

                viewModelScope.launch {
                    eventChannel.send(RegisterViewModelEvent.Success)
                }
            }
        }
    }

    sealed class RegisterViewModelEvent {
        data object Success : RegisterViewModelEvent()
        data class Error(val error: String) : RegisterViewModelEvent()
    }
}