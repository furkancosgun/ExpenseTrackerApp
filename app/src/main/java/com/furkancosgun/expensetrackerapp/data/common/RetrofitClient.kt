package com.furkancosgun.expensetrackerapp.data.common

import com.furkancosgun.expensetrackerapp.data.datasource.AuthDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.40:9000/api/v1/"

    private var instance: Retrofit? = null
    private fun getClient(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

    fun getAuthService(): AuthDataSource {
        return getClient().create(AuthDataSource::class.java)
    }
}