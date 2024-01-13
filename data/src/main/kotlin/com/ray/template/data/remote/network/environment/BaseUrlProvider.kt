package com.ray.template.data.remote.network.environment

import com.ray.template.data.remote.local.SharedPreferencesManager

class BaseUrlProvider(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun get(): String {
        val isDev: Boolean = false

        if (isDev) {
            return DEV_BASE_URL
        } else {
            return RELEASE_BASE_URL
        }
    }

    companion object {
        private const val DEV_BASE_URL = "https://api.musixmatch.com/"
        private const val RELEASE_BASE_URL = "https://api.musixmatch.com/"
    }
}
