package com.app.androidapptemplate.di

import com.app.androidapptemplate.network.apiclient.ApiHelper
import com.app.androidapptemplate.repository.AppRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiHelperModule {

    @Binds
    @Singleton
    abstract fun bindApiHelper(appRepo: AppRepo): ApiHelper
}
