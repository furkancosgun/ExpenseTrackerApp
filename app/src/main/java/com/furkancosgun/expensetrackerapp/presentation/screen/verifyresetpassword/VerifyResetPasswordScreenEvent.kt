package com.furkancosgun.expensetrackerapp.presentation.screen.verifyresetpassword

sealed class VerifyResetPasswordScreenEvent {
    data class OtpCodeChanged(val otpCode: String) : VerifyResetPasswordScreenEvent()
    data object Submit : VerifyResetPasswordScreenEvent()
}
