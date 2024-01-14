package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.RegisterRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
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
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val authDataSource: RetrofitAuthDataSource,
) : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set
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
                submitData()
            }
        }
    }

    private fun submitData() {
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
            sendRegisterRequest()
        }
    }

    private suspend fun sendRegisterRequest() {
        httpRequestHandler(request = {
            authDataSource.register(
                RegisterRequest(
                    firstName = state.firstName,
                    lastName = state.lastName,
                    email = state.email,
                    password = state.password
                )
            )
        }, onSuccess = { _ ->
            sendEvent(RegisterViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(RegisterViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: RegisterViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }


    sealed class RegisterViewModelEvent {
        data object Success : RegisterViewModelEvent()
        data class Error(val error: String) : RegisterViewModelEvent()
    }
}