package com.furkancosgun.expensetrackerapp.presentation.screen.register

import androidx.compose.material3.SnackbarHostState

data class RegisterScreenState(
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
