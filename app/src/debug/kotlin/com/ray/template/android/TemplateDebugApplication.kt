package com.ray.template.android

import timber.log.Timber

class TemplateDebugApplication : TemplateApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
