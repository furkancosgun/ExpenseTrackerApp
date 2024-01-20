package com.furkancosgun.expensetrackerapp.data.datasource

import com.furkancosgun.expensetrackerapp.data.model.request.CreateProjectRequest
import com.furkancosgun.expensetrackerapp.data.model.response.GetProjectResponse
import com.furkancosgun.expensetrackerapp.data.model.response.ProjectReportResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectDataSource {
    @POST("project/create")
    suspend fun createProject(@Body request: CreateProjectRequest): Response<Unit>

    @POST("project/report-list")
    suspend fun getProjectReport(): Response<List<ProjectReportResponse>>

    @POST("project/list")
    suspend fun getProjects(): Response<List<GetProjectResponse>>
}