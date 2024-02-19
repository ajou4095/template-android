package com.ray.template.android.data.remote.mapper

interface DataMapper<D> {
    fun toDomain(): D
}
