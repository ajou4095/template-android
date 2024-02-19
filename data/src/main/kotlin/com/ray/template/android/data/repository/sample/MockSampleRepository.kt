package com.ray.template.android.data.repository.sample

import com.ray.template.android.domain.model.sample.SampleInformation
import com.ray.template.android.domain.repository.SampleRepository
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
