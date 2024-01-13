package com.ray.template.domain.repository

import com.ray.template.domain.model.SampleInformation

interface SampleRepository {
    suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation>
}
