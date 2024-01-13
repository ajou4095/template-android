package com.ray.template.data.remote.mapper

interface DataMapper<D> {
    fun toDomain(): D
}
