package com.ray.template.android.domain.usecase.authentication.token

import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenRefreshFailEventFlowUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): EventFlow<Unit> {
        return tokenRepository.refreshFailEvent
    }
}
