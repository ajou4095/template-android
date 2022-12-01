package com.ray.template.data.repository

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.util.convertResponseToDomain
import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

// TODO : Coroutine Dispatcher Injection
class RealSampleRepository(
    private val sampleApi: SampleApi
) : SampleRepository {
    override fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Flow<SampleInformation> {
        return sampleApi.getSampleInformation(
            apiKey = apiKey,
            title = title,
            artist = artist
        ).convertResponseToDomain()
            .flowOn(Dispatchers.IO)
    }
}