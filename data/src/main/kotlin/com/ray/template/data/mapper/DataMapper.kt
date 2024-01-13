package com.ray.template.data.mapper

interface DataMapper<D> {
    fun toDomain(): D
}