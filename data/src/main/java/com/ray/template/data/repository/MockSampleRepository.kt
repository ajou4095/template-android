package com.ray.template.data.repository

import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockSampleRepository : SampleRepository {
    override fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Flow<SampleInformation> {
        return flow {
            emit(SampleInformation())
        }
    }
}