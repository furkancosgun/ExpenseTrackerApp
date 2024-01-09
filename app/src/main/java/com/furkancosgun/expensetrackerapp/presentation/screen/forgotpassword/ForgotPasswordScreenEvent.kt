package com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword

sealed class ForgotPasswordScreenEvent {
    data class EmailChanged(val email: String) : ForgotPasswordScreenEvent()
    data object Submit : ForgotPasswordScreenEvent()
}
