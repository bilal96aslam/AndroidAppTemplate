package com.app.androidapptemplate.network.apiclient

import com.app.androidapptemplate.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LoginResponse>
}