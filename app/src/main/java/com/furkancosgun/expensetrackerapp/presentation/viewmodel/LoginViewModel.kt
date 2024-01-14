package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.LoginRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.UserSessionManagerRepository
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.model.UserSessionModel
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.screen.login.LoginScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.login.LoginScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authDataSource: RetrofitAuthDataSource,
    private val userSessionManagerRepository: UserSessionManagerRepository,
) : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set

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
                submitData()
            }
        }
    }

    private fun submitData() {
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

        viewModelScope.launch(Dispatchers.IO) {
            sendLoginRequest()
        }
    }

    private suspend fun sendLoginRequest() {
        httpRequestHandler(request = {
            authDataSource.login(
                LoginRequest(
                    state.email,
                    state.password
                )
            )
        }, onSuccess = { response ->
            val userSession = UserSessionModel(
                response.firstName,
                response.lastName,
                response.email,
                response.token
            )
            userSessionManagerRepository.loginUser(userSession)
            sendEvent(LoginViewModelEvent.Success)
        }, onError = { errorMessage ->
            sendEvent(LoginViewModelEvent.Error(errorMessage))
        })
    }

    private fun sendEvent(event: LoginViewModelEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    sealed class LoginViewModelEvent {
        data object Success : LoginViewModelEvent()
        data class Error(val error: String) : LoginViewModelEvent()
    }
}