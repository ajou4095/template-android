package com.ray.template.android.data.remote.network.environment

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ray.template.android.data.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class BaseUrlProvider(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun get(): String {
        val preferencesKey = stringPreferencesKey(KEY_SERVER_FLAG)
        val defaultFlag = context.getString(R.string.server_flag)

        val serverFlag: String = dataStore.data.map { preferences ->
            preferences[preferencesKey]
        }.first() ?: let {
            dataStore.edit { preferences ->
                preferences[preferencesKey] = defaultFlag
            }
            defaultFlag
        }

        when (serverFlag) {
            SERVER_FLAG_DEVELOPMENT -> return DEVELOPMENT_BASE_URL
            SERVER_FLAG_PRODUCTION -> return PRODUCTION_BASE_URL
        }

        throw IllegalArgumentException("Invalid server flag")
    }

    companion object {
        private const val DEVELOPMENT_BASE_URL = "https://dev.api.musixmatch.com/"
        private const val PRODUCTION_BASE_URL = "https://api.musixmatch.com/"

        private const val KEY_SERVER_FLAG = "server_flag"
        const val SERVER_FLAG_DEVELOPMENT = "development"
        const val SERVER_FLAG_PRODUCTION = "production"
    }
}
