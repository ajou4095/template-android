package com.ray.template.android.data.repository.authentication

import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.domain.model.authentication.JwtToken
import com.ray.template.android.domain.model.error.ServerException
import com.ray.template.android.domain.repository.AuthenticationRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockAuthenticationRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : AuthenticationRepository {

    override var refreshToken: String
        set(value) = sharedPreferencesManager.setString(REFRESH_TOKEN, value)
        get() = sharedPreferencesManager.getString(REFRESH_TOKEN, "")

    override var accessToken: String
        set(value) = sharedPreferencesManager.setString(ACCESS_TOKEN, value)
        get() = sharedPreferencesManager.getString(ACCESS_TOKEN, "")

    private var isRegistered: Boolean
        set(value) = sharedPreferencesManager.setBoolean(IS_REGISTERED, value)
        get() = sharedPreferencesManager.getBoolean(IS_REGISTERED, false)

    override suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken> {
        randomShortDelay()
        return Result.success(
            JwtToken(
                accessToken = "mock_access_token",
                refreshToken = "mock_refresh_token"
            )
        ).onSuccess { token ->
            this.refreshToken = token.refreshToken
            this.accessToken = token.accessToken
        }.map { token ->
            JwtToken(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
        }
    }

    override suspend fun login(
        username: String,
        password: String
    ): Result<Long> {
        randomShortDelay()
        return Result.success(0L).onSuccess { token ->
            this.refreshToken = "mock_access_token"
            this.accessToken = "mock_refresh_token"
        }
    }

    override suspend fun logout(): Result<Unit> {
        randomShortDelay()
        return when {
            accessToken.isEmpty() || refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            !isRegistered -> {
                Result.failure(ServerException("MOCK_ERROR", "가입된 사용자가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            this.refreshToken = ""
            this.accessToken = ""
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
            this.refreshToken = "mock_access_token"
            this.accessToken = "mock_refresh_token"
        }
    }

    override suspend fun withdraw(): Result<Unit> {
        randomLongDelay()
        return when {
            accessToken.isEmpty() || refreshToken.isEmpty() -> {
                Result.failure(ServerException("MOCK_ERROR", "로그인 상태가 아닙니다."))
            }

            !isRegistered -> {
                Result.failure(ServerException("MOCK_ERROR", "가입된 사용자가 아닙니다."))
            }

            else -> {
                Result.success(Unit)
            }
        }.onSuccess {
            this.refreshToken = ""
            this.accessToken = ""
        }
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 500).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }

    companion object {
        private const val REFRESH_TOKEN = "mock_refresh_token"
        private const val ACCESS_TOKEN = "mock_access_token"
        private const val IS_REGISTERED = "mock_is_registered"
    }
}
