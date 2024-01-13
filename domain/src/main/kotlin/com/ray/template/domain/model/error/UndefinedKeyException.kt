package com.ray.template.domain.model.error

class UndefinedKeyException(
    override val message: String
) : Exception(message)
