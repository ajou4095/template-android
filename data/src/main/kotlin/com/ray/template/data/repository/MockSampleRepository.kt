package com.ray.template.data.repository

import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository

class MockSampleRepository : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return Result.success(SampleInformation())
    }
}
