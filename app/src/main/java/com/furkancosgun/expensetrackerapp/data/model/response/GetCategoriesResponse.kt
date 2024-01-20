package com.furkancosgun.expensetrackerapp.data.model.response

import com.google.gson.annotations.SerializedName

data class GetCategoriesResponse(
    @SerializedName("CategoryId")
    val categoryId: String,
    @SerializedName("CategoryName")
    val categoryName: String
)