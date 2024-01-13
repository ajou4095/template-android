package com.ray.template.data.repository

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.util.toDomain
import com.ray.template.domain.model.sample.SampleInformation
import com.ray.template.domain.repository.sample.SampleRepository

class RealSampleRepository(
    private val sampleApi: SampleApi
) : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return sampleApi.getLyrics(
            apiKey = apiKey,
            title = title,
            artist = artist
        ).toDomain()
    }
}
