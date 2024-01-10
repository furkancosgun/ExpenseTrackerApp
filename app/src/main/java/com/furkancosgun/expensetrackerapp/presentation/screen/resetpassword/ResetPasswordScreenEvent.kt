package com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword

sealed class ResetPasswordScreenEvent {
    data class PasswordChanged(val password: String) : ResetPasswordScreenEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : ResetPasswordScreenEvent()
    data object Submit : ResetPasswordScreenEvent()
}
