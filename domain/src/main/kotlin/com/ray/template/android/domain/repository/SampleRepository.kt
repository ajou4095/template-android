package com.ray.template.android.domain.repository

import com.ray.template.android.domain.model.sample.SampleInformation

interface SampleRepository {
    suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation>
}
