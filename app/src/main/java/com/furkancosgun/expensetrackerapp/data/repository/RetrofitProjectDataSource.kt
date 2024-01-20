package com.furkancosgun.expensetrackerapp.data.repository

import com.furkancosgun.expensetrackerapp.data.common.RetrofitClient
import com.furkancosgun.expensetrackerapp.data.datasource.ProjectDataSource
import com.furkancosgun.expensetrackerapp.data.model.request.CreateProjectRequest
import com.furkancosgun.expensetrackerapp.data.model.response.GetProjectResponse
import com.furkancosgun.expensetrackerapp.data.model.response.ProjectReportResponse
import retrofit2.Response

class RetrofitProjectDataSource : ProjectDataSource {
    private val projectService = RetrofitClient.getProjectService()
    override suspend fun createProject(request: CreateProjectRequest): Response<Unit> {
        return projectService.createProject(request)
    }

    override suspend fun getProjectReport(): Response<List<ProjectReportResponse>> {
        return projectService.getProjectReport()
    }

    override suspend fun getProjects(): Response<List<GetProjectResponse>> {
        return projectService.getProjects()
    }
}