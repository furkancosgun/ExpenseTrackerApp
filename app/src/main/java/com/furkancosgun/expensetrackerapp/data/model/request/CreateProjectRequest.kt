package com.furkancosgun.expensetrackerapp.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateProjectRequest(
    @SerializedName("name") val name: String
)
