package com.furkancosgun.expensetrackerapp.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("Token")
    val token: String,
)
