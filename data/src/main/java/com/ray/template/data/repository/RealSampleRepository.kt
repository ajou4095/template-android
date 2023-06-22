package com.ray.template.data.repository

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.util.convertResponseToDomain
import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository

class RealSampleRepository(
    private val sampleApi: SampleApi
) : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return sampleApi.getSampleInformation(
            apiKey = apiKey,
            title = title,
            artist = artist
        ).convertResponseToDomain()
    }
}
