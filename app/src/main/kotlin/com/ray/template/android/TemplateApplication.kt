package com.ray.template.android

import android.app.Application
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
        initializeKakaoTalk()
    }

    private fun initializeFirebase() {
        Firebase.analytics
        Firebase.crashlytics
    }

    private fun initializeKakaoTalk() {
        KakaoSdk.init(this, getString(R.string.key_kakao_app))
    }
}
