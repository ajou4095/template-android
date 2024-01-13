package com.ray.template.data.repository.sample

import com.ray.template.domain.model.sample.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import javax.inject.Inject

class MockSampleRepository @Inject constructor() : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return Result.success(SampleInformation())
    }
}
