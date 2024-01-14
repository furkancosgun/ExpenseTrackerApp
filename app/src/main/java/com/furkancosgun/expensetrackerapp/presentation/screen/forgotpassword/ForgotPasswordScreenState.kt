package com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword

import androidx.compose.material3.SnackbarHostState

data class ForgotPasswordScreenState(
    val email: String = "",
    val emailError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
