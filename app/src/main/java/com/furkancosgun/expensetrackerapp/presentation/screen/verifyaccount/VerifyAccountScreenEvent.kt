package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

sealed class VerifyAccountScreenEvent {
    data class OtpCodeChanged(val otpCode: String) : VerifyAccountScreenEvent()
    data object Submit : VerifyAccountScreenEvent()
}
