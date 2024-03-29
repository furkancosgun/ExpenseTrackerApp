package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

import androidx.compose.material3.SnackbarHostState

data class VerifyAccountScreenState(
    val otpCode: String = "",
    val otpCodeError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
