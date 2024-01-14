package com.furkancosgun.expensetrackerapp.presentation.screen.verifyresetpassword

import androidx.compose.material3.SnackbarHostState

data class VerifyResetPasswordScreenState(
    val otpCode: String = "",
    val otpCodeError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
