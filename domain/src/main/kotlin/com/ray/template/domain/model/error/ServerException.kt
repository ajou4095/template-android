package com.ray.template.domain.model.error

open class ServerException(
    open val id: String,
    override val message: String
) : Exception(message)
