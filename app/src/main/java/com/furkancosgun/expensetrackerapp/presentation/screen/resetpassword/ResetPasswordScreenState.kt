package com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword

import androidx.compose.material3.SnackbarHostState

data class ResetPasswordScreenState(
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
