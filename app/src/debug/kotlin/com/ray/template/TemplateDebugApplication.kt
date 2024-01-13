package com.ray.template

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
import com.ray.template.data.remote.local.SharedPreferencesManager
import leakcanary.LeakCanary
import timber.log.Timber

class TemplateDebugApplication : TemplateApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        LeakCanary.config = LeakCanary.config.copy(
            onHeapAnalyzedListener = FlipperLeakListener()
        )

        SoLoader.init(this, false)

        if (FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).run {
                addPlugin(InspectorFlipperPlugin(this@TemplateDebugApplication, DescriptorMapping.withDefaults()))
                addPlugin(NetworkFlipperPlugin())
                addPlugin(NavigationFlipperPlugin.getInstance())
                addPlugin(DatabasesFlipperPlugin(this@TemplateDebugApplication))
                addPlugin(CrashReporterPlugin.getInstance())
                addPlugin(LeakCanary2FlipperPlugin())
                addPlugin(
                    SharedPreferencesFlipperPlugin(
                        this@TemplateDebugApplication,
                        SharedPreferencesManager.SHARED_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE
                    )
                )
                start()
            }
            setCrashHandler()
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