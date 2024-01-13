package com.ray.template.data.repository

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.model.SampleInformationRes
import com.ray.template.data.remote.network.util.convertResponseToDomain
import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get

class RealSampleRepository(
    private val client: HttpClient
) : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return client.get(
            SampleApi.GetLyrics(
                apiKey = apiKey,
                title = title,
                artist = artist
            )
        ).convertResponseToDomain<SampleInformationRes, SampleInformation>()
    }
}
