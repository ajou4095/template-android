package com.ray.template.android.data.repository.nonfeature.authentication

import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockAuthenticationRepository @Inject constructor(
    private val tokenRepository: TokenRepository
) : AuthenticationRepository {

    override suspend fun logout(): Result<Unit> {
        randomShortDelay()
        return when {
            tokenRepository.accessToken.isEmpty() || tokenRepository.refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            tokenRepository.refreshToken = ""
            tokenRepository.accessToken = ""
        }
    }

    override suspend fun withdraw(): Result<Unit> {
        randomLongDelay()
        return when {
            tokenRepository.accessToken.isEmpty() || tokenRepository.refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            tokenRepository.refreshToken = ""
            tokenRepository.accessToken = ""
        }
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 500).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }

    companion object {
        private const val IS_REGISTERED = "mock_is_registered"
    }
}
