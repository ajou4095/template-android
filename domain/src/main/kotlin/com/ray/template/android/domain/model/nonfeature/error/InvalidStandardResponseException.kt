package com.ray.template.android.domain.model.nonfeature.error

class InvalidStandardResponseException(
    override val message: String
) : Exception(message)
