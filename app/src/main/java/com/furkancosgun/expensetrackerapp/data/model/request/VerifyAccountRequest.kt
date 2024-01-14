package com.furkancosgun.expensetrackerapp.data.model.request

import com.google.gson.annotations.SerializedName

data class VerifyAccountRequest(
    @SerializedName("email") var email: String,
    @SerializedName("otp") var otp: String
)
