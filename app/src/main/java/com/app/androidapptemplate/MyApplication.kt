package com.app.androidapptemplate

import android.app.Application
import android.util.Log
import com.assignment.androidapptemplate.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Log.e("TIMBER", "Timber Start")
        } else {
            Log.e("TIMBER", "Timber Stop")
        }
    }

}
