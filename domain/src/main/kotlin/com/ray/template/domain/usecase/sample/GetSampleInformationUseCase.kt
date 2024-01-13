package com.ray.template.domain.usecase.sample

import com.ray.template.domain.model.sample.SampleInformation
import com.ray.template.domain.repository.SampleRepository
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
