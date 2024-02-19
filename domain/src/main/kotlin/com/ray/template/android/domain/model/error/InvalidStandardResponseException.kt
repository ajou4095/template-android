package com.ray.template.android.domain.model.error

class InvalidStandardResponseException(
    override val message: String
) : Exception(message)
