package com.ray.template.android.domain.usecase.sample

import com.ray.template.android.domain.model.sample.SampleInformation
import com.ray.template.android.domain.repository.SampleRepository
import javax.inject.Inject

class GetSampleInformationUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    suspend operator fun invoke(): Result<SampleInformation> {
        return sampleRepository.getSampleInformation(
            apiKey = "",
            title = "",
            artist = ""
        )
    }
}
