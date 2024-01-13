package com.ray.template.domain.model.error

class InvalidStandardResponseException(
    override val message: String
) : Exception(message)
