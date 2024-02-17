package com.ray.template.presentation.common.base

import com.ray.template.domain.model.error.ServerException

sealed class ErrorEvent(
    open val exception: Throwable
) {
    data class Client(override val exception: Throwable) : ErrorEvent(exception)
    data class InvalidRequest(override val exception: ServerException) : ErrorEvent(exception)
    data class UnavailableServer(override val exception: Throwable) : ErrorEvent(exception)
}
