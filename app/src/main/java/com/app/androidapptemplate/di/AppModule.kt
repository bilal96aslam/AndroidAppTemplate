package com.app.androidapptemplate.di

import android.content.Context
import com.app.androidapptemplate.data.PreferencesHelper
import com.app.androidapptemplate.data.PreferencesHelperImpl
import com.app.androidapptemplate.network.apiclient.ApiService
import com.app.androidapptemplate.repository.AppRepo
import com.app.androidapptemplate.utils.Constant.BASE_URL
import com.app.androidapptemplate.utils.Constant.PREF_FILE_NAME
import com.app.androidapptemplate.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun okHttp(): OkHttpClient {
        val tlsSpecs = listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)
        return OkHttpClient.Builder()
            .connectionSpecs(tlsSpecs)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(client: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context.applicationContext)
    }

    @Singleton
    @Provides
    fun providePreferenceHelper(preferencesHelperImpl: PreferencesHelperImpl): PreferencesHelper {
        return preferencesHelperImpl
    }

    @Provides
    @Singleton
    fun providePreferenceHelperImpl(
        @ApplicationContext context: Context
    ): PreferencesHelperImpl {
        return PreferencesHelperImpl(context, PREF_FILE_NAME)
    }

    @Provides
    @Singleton
    fun provideAppRepo(
        resourceProvider: ResourceProvider,
        apiService: ApiService
    ): AppRepo {
        return AppRepo(resourceProvider, apiService)
    }
}