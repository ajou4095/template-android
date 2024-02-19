package com.ray.template.android.domain.model.error

class UndefinedKeyException(
    override val message: String
) : Exception(message)
