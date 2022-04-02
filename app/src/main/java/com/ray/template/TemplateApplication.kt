package com.ray.template

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakListener
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.ray.template.data.local.SharedPreferencesManager
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
import timber.log.Timber

@HiltAndroidApp
class TemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

            LeakCanary.config = LeakCanary.config.copy(
                onHeapAnalyzedListener = FlipperLeakListener()
            )

            SoLoader.init(this, false)

            if (FlipperUtils.shouldEnableFlipper(this)) {
                AndroidFlipperClient.getInstance(this).run {
                    addPlugin(InspectorFlipperPlugin(this@TemplateApplication, DescriptorMapping.withDefaults()))
                    addPlugin(NetworkFlipperPlugin())
                    addPlugin(NavigationFlipperPlugin.getInstance())
                    addPlugin(DatabasesFlipperPlugin(this@TemplateApplication))
                    addPlugin(CrashReporterPlugin.getInstance())
                    addPlugin(LeakCanary2FlipperPlugin())
                    addPlugin(
                        SharedPreferencesFlipperPlugin(
                            this@TemplateApplication,
                            SharedPreferencesManager.SHARED_PREFERENCE_FILE_NAME,
                            Context.MODE_PRIVATE
                        )
                    )
                    start()
                }
                setCrashHandler()
            }

        }
    }

    private fun setCrashHandler() {
        val originHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            CrashReporterPlugin.getInstance().sendExceptionMessage(thread, throwable)
            originHandler?.uncaughtException(thread, throwable)
        }
    }
}