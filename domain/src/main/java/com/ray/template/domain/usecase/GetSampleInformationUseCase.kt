package com.ray.template.domain.usecase

import com.ray.template.domain.model.SampleInformation
import com.ray.template.domain.repository.SampleRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSampleInformationUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    operator fun invoke(): Flow<SampleInformation> {
        return sampleRepository.getSampleInformation(
            apiKey = "",
            title = "",
            artist = ""
        )
    }
}