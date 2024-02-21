package com.ray.template.android.data.remote.network.environment

import android.content.Context
import com.ray.template.android.data.R
import com.ray.template.android.domain.model.nonfeature.error.UndefinedKeyException
import timber.log.Timber

class ErrorMessageMapper(
    private val context: Context
) {
    fun map(
        key: String
    ): String {
        val id = when (key) {
            KEY_INTERNAL_SERVER_ERROR -> R.string.error_internal_server_error

            else -> {
                Timber.e(UndefinedKeyException("Undefined error key: $key"))
                R.string.error_unknown
            }
        }

        return context.getString(id)
    }

    companion object {
        const val KEY_INTERNAL_SERVER_ERROR = "SERVER:INTERNAL_SERVER_ERROR"
    }
}
