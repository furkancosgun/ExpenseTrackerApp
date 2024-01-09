package com.furkancosgun.expensetrackerapp.presentation.screen.register


sealed class RegisterScreenEvent {
    data class FirstNameChanged(val firstName: String) : RegisterScreenEvent()
    data class LastNameChanged(val lastName: String) : RegisterScreenEvent()
    data class EmailChanged(val email: String) : RegisterScreenEvent()
    data class PasswordChanged(val password: String) : RegisterScreenEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegisterScreenEvent()
    data object Submit : RegisterScreenEvent()
}
