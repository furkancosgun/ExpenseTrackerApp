package com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword

sealed class ForgotPasswordScreenEvent {
    data class EmailChanged(val email: String) : ForgotPasswordScreenEvent()
    data object Submit : ForgotPasswordScreenEvent()
}
