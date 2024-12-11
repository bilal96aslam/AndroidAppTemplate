package com.app.androidapptemplate.network.apiclient

import com.app.androidapptemplate.network.apiclient.base.ApiResponse
import com.app.androidapptemplate.network.response.LoginResponse

interface ApiHelper {
    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<LoginResponse>
}