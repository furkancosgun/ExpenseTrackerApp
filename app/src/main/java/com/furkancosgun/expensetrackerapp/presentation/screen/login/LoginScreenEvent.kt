package com.furkancosgun.expensetrackerapp.presentation.screen.login

sealed class LoginScreenEvent {
    data class EmailChanged(val email: String) : LoginScreenEvent()
    data class PasswordChanged(val password: String) : LoginScreenEvent()
    data object Submit : LoginScreenEvent()
}
