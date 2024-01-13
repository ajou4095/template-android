package com.ray.template.data.remote.network.util

import com.ray.template.data.remote.mapper.DataMapper
import com.ray.template.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.data.remote.network.model.error.ErrorRes
import com.ray.template.domain.model.error.BadRequestServerException
import com.ray.template.domain.model.error.InternalServerException
import com.ray.template.domain.model.error.InvalidStandardResponseException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.sentry.Sentry
import io.sentry.SentryLevel
import timber.log.Timber

val HttpResponse.isSuccessful: Boolean
    get() = status.value in 200..299

val HttpResponse.isBadRequest: Boolean
    get() = status.value in 400..499


val HttpResponse.isInternalServerError: Boolean
    get() = status.value in 500..599

inline fun <reified T : DataMapper<R>, R : Any> Result<T>.toDomain(): Result<R> {
    return map { it.toDomain() }
}

suspend inline fun <reified T : Any> HttpResponse.convert(
    noinline errorMessageMapper: (String) -> String
): Result<T> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body<T?>() ?: throw InvalidStandardResponseException("Response Empty Body")
        } else {
            throw this.toThrowable(errorMessageMapper)
        }
    }.onFailure { exception ->
        Timber.d(exception)
        Sentry.withScope {
            it.level = SentryLevel.INFO
            Sentry.captureException(exception)
        }
    }
}

suspend inline fun HttpResponse.toThrowable(
    noinline errorMessageMapper: (String) -> String
): Throwable {
    return runCatching {
        if (isInternalServerError) {
            return@runCatching InternalServerException(
                ErrorMessageMapper.KEY_INTERNAL_SERVER_ERROR,
                errorMessageMapper(ErrorMessageMapper.KEY_INTERNAL_SERVER_ERROR)
            )
        }

        return@runCatching this.body<ErrorRes?>()?.let { errorRes ->
            BadRequestServerException(errorRes.id, errorMessageMapper(errorRes.id))
        } ?: InvalidStandardResponseException("Response Empty Body")
    }.getOrElse { exception ->
        exception
    }
}
