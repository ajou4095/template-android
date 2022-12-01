package com.ray.template.data.remote.local

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCE_FILE_NAME,
        Context.MODE_PRIVATE
    )

    fun setBoolean(
        key: String,
        value: Boolean
    ) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getBoolean(
        key: String,
        defaultValue: Boolean = false
    ): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun setFloat(
        key: String,
        value: Float
    ) {
        sharedPreferences.edit {
            putFloat(key, value)
        }
    }

    fun getFloat(
        key: String,
        defaultValue: Float = 0f
    ): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun setInt(
        key: String,
        value: Int
    ) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    fun getInt(
        key: String,
        defaultValue: Int = 0
    ): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun setLong(
        key: String,
        value: Long
    ) {
        sharedPreferences.edit {
            putLong(key, value)
        }
    }

    fun getLong(
        key: String,
        defaultValue: Long = 0L
    ): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun setString(
        key: String,
        value: String?
    ) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun getString(
        key: String,
        defaultValue: String = ""
    ): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun remove(key: String) {
        sharedPreferences.edit {
            this.remove(key)
        }
    }

    companion object {
        const val SHARED_PREFERENCE_FILE_NAME = "com.ray.template.prefs"
    }
}