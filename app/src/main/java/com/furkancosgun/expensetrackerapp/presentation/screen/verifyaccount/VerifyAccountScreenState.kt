package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

data class VerifyAccountScreenState(
    val otpCode: String = "",
    val otpCodeError: String? = null
)
