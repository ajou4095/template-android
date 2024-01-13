package com.ray.template.domain.repository

import com.ray.template.domain.model.sample.SampleInformation

interface SampleRepository {
    suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation>
}
