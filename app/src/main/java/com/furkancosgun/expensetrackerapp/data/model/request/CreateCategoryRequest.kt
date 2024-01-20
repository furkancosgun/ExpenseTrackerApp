package com.furkancosgun.expensetrackerapp.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateCategoryRequest(
    @SerializedName("Name")
    val name: String
)
