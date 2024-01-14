package com.furkancosgun.expensetrackerapp.domain.model

data class UserSessionModel(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val token: String = "",
)
