package com.furkancosgun.expensetrackerapp.presentation.screen.login

import androidx.compose.material3.SnackbarHostState

data class LoginScreenState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
