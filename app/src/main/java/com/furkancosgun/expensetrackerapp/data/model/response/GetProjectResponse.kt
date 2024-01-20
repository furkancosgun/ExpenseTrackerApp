package com.furkancosgun.expensetrackerapp.data.model.response

import com.google.gson.annotations.SerializedName

data class GetProjectResponse(
    @SerializedName("ProjectId")
    val projectId: String,
    @SerializedName("ProjectName")
    val projectName: String,
)