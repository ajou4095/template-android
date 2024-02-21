package com.ray.template.android.domain.usecase.nonfeature.tracking

import androidx.annotation.Size
import com.ray.template.android.domain.repository.nonfeature.TrackingRepository
import javax.inject.Inject

class LogEventUseCase @Inject constructor(
    private val trackingRepository: TrackingRepository
) {
    suspend operator fun invoke(
        @Size(min = 1, max = 40) eventName: String,
        params: Map<String, Any>
    ): Result<Unit> {
        return trackingRepository.logEvent(
            eventName = eventName,
            params = params
        )
    }
}
