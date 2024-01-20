package com.furkancosgun.expensetrackerapp.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateExpenseRequest(
    @SerializedName("projectId") var projectId: String? = null,
    @SerializedName("merchantName") var merchantName: String? = null,
    @SerializedName("amount") var amount: Double? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("categoryId") var categoryId: String? = null,
    @SerializedName("includeVat") var includeVat: Boolean? = null,
    @SerializedName("vat") var vat: Double? = null,
)
