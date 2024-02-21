package com.ray.template.android.data.repository.nonfeature.authentication

import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockAuthenticationRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val tokenRepository: TokenRepository
) : AuthenticationRepository {

    private var isRegistered: Boolean
        set(value) = sharedPreferencesManager.setBoolean(IS_REGISTERED, value)
        get() = sharedPreferencesManager.getBoolean(IS_REGISTERED, false)

    override suspend fun login(
        username: String,
        password: String
    ): Result<Long> {
        randomShortDelay()
        return Result.success(0L).onSuccess { token ->
            tokenRepository.refreshToken = "mock_access_token"
            tokenRepository.accessToken = "mock_refresh_token"
        }
    }

    override suspend fun logout(): Result<Unit> {
        randomShortDelay()
        return when {
            tokenRepository.accessToken.isEmpty() || tokenRepository.refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            !isRegistered -> {
                Result.failure(ServerException("MOCK_ERROR", "가입된 사용자가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            tokenRepository.refreshToken = ""
            tokenRepository.accessToken = ""
        }
    }

    override suspend fun register(
        username: String,
        password: String
    ): Result<Long> {
        randomLongDelay()
        return if (isRegistered) {
            Result.failure(ServerException("MOCK_ERROR", "이미 가입된 사용자입니다."))
        } else {
            Result.success(0L)
        }.onSuccess {
            tokenRepository.refreshToken = "mock_access_token"
            tokenRepository.accessToken = "mock_refresh_token"
            isRegistered = true
        }
    }

    override suspend fun withdraw(): Result<Unit> {
        randomLongDelay()
        return when {
            tokenRepository.accessToken.isEmpty() || tokenRepository.refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            !isRegistered -> {
                Result.failure(ServerException("MOCK_ERROR", "가입된 사용자가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            tokenRepository.refreshToken = ""
            tokenRepository.accessToken = ""
            isRegistered = false
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
