package com.ray.template.android

import android.app.Application
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

@HiltAndroidApp
open class TemplateApplication : Application() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.d(exception)
        Sentry.captureException(exception)
        Firebase.crashlytics.recordException(exception)
    }

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
    }

    private fun initializeFirebase() {
        Firebase.analytics
    }
}
