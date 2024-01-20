package com.furkancosgun.expensetrackerapp.data.common

import com.furkancosgun.expensetrackerapp.data.datasource.AuthDataSource
import com.furkancosgun.expensetrackerapp.data.datasource.CategoryDataSource
import com.furkancosgun.expensetrackerapp.data.datasource.ExpenseDataSource
import com.furkancosgun.expensetrackerapp.data.datasource.ProjectDataSource
import com.furkancosgun.expensetrackerapp.data.repository.UserSessionManagerRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.41:9000/api/v1/"

    private var instance: Retrofit? = null
    private fun getClient(): Retrofit {
        if (instance == null) {
            val authClient = OkHttpClient.Builder().addInterceptor(
                AuthInterceptor(
                    "Bearer",
                    accessToken = UserSessionManagerRepository.userSession.token
                )
            ).build()

            instance = Retrofit.Builder()
                .client(authClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

    fun getAuthService(): AuthDataSource {
        return getClient().create(AuthDataSource::class.java)
    }

    fun getProjectService(): ProjectDataSource {
        return getClient().create(ProjectDataSource::class.java)
    }

    fun getCategoryService(): CategoryDataSource {
        return getClient().create(CategoryDataSource::class.java)
    }

    fun getExpenseService(): ExpenseDataSource {
        return getClient().create(ExpenseDataSource::class.java)
    }
}