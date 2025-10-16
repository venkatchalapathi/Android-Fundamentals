package com.venkat.cleanarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CleanArchitectureApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-wide dependencies here if needed
    }
}

