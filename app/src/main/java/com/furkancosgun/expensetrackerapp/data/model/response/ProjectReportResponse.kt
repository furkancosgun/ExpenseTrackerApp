package com.furkancosgun.expensetrackerapp.data.model.response

import com.google.gson.annotations.SerializedName

data class ProjectReportResponse(
    @SerializedName("ProjectId") var projectId: String? = null,
    @SerializedName("ProjectName") var projectName: String? = null,
    @SerializedName("TotalAmount") var totalAmount: Double? = null,
    @SerializedName("CreatedAt") var createdAt: String? = null,
    @SerializedName("TotalExpenses") var totalExpenses: Int? = null
)
