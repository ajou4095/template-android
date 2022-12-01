package com.ray.template.presentation.helper.main

import android.content.Context
import android.content.Intent
import com.ray.template.presentation.ui.main.MainActivity

/**
 * Helper class
 *
 * 1. Circular Dependency 를 방지한다.
 * 2. Bundle 을 Activity / Fragment / ViewModel 대신 관리해준다.
 */
object MainActivityHelper {
    fun getNavigationIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }
}