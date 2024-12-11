package com.app.androidapptemplate.repository

import com.app.androidapptemplate.base.BaseRepository
import com.app.androidapptemplate.network.apiclient.ApiHelper
import com.app.androidapptemplate.network.apiclient.ApiService
import com.app.androidapptemplate.network.apiclient.base.ApiResponse
import com.app.androidapptemplate.network.response.LoginResponse
import com.app.androidapptemplate.utils.ResourceProvider
import javax.inject.Inject

class AppRepo @Inject constructor(
    resourceProvider: ResourceProvider,
    private val apiService: ApiService
) : BaseRepository(resourceProvider), ApiHelper {

    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return safeApiCall { apiService.login(email, password) }
    }
}