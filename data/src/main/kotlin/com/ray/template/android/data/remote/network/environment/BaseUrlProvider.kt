package com.ray.template.android.data.remote.network.environment

import android.content.Context
import com.ray.template.android.data.R
import com.ray.template.android.data.remote.local.SharedPreferencesManager

class BaseUrlProvider(
    private val context: Context,
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun get(): String {
        val serverFlag = sharedPreferencesManager.getString(
            SERVER_FLAG,
            context.getString(R.string.server_flag)
        )

        when (serverFlag) {
            SERVER_FLAG_DEVELOPMENT -> return DEVELOPMENT_BASE_URL
            SERVER_FLAG_PRODUCTION -> return PRODUCTION_BASE_URL
        }

        throw IllegalArgumentException("Invalid server flag")
    }

    companion object {
        private const val DEVELOPMENT_BASE_URL = "https://dev.api.musixmatch.com/"
        private const val PRODUCTION_BASE_URL = "https://api.musixmatch.com/"

        private const val SERVER_FLAG = "server_flag"
        const val SERVER_FLAG_DEVELOPMENT = "development"
        const val SERVER_FLAG_PRODUCTION = "production"
    }
}
