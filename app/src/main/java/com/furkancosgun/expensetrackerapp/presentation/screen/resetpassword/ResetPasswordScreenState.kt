package com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword

data class ResetPasswordScreenState(
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
)
