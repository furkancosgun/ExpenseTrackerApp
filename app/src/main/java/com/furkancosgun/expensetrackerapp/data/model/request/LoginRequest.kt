package com.furkancosgun.expensetrackerapp.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Email")
    var email: String,

    @SerializedName("Password")
    var password: String
)
