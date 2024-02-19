package com.ray.template.android.domain.model.error

class BadRequestServerException(
    override val id: String,
    override val message: String
) : ServerException(id, message)
