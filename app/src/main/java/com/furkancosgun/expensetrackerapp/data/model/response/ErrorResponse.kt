package com.furkancosgun.expensetrackerapp.data.model.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class ErrorResponse(
    @SerializedName("ErrorDescription")
    val errorDescription: String
) {
    companion object {
        fun fromResponse(response: okhttp3.ResponseBody?): ErrorResponse {
            return Gson().fromJson(
                response?.charStream(),
                object : TypeToken<ErrorResponse>() {}.type
            )
        }
    }
}
