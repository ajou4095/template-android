package com.ray.template.android

import android.app.Application
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class TemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Firebase Initialize
        Firebase.analytics
    }
}
