package com.ray.template.android

import android.app.Application
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.ray.template.android.domain.repository.AuthenticationRepository
import com.ray.template.android.presentation.ui.invalid.InvalidJwtTokenActivity
import dagger.hilt.android.HiltAndroidApp
import io.sentry.Sentry
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltAndroidApp
open class TemplateApplication : Application() {

    @Inject
    lateinit var authenticationRepository: AuthenticationRepository

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.d(exception)
        Sentry.captureException(exception)
        Firebase.crashlytics.recordException(exception)
    }

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
        observeRefreshTokenValidation()
    }

    private fun initializeFirebase() {
        Firebase.analytics
    }

    private fun observeRefreshTokenValidation() {
        with(ProcessLifecycleOwner.get()) {
            lifecycleScope.launch(handler) {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    authenticationRepository.isRefreshTokenInvalid.collect { isRefreshTokenInvalid ->
                        if (isRefreshTokenInvalid) {
                            val intent = Intent(
                                this@TemplateApplication,
                                InvalidJwtTokenActivity::class.java
                            ).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}
