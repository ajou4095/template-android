package com.ray.template.data.repository.sample

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.util.toDomain
import com.ray.template.domain.model.sample.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import javax.inject.Inject

class RealSampleRepository @Inject constructor(
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
