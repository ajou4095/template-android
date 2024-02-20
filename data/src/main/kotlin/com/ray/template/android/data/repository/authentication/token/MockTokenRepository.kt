package com.ray.template.android.data.repository.authentication.token

import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.domain.model.authentication.JwtToken
import com.ray.template.android.domain.model.error.ServerException
import com.ray.template.android.domain.repository.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MockTokenRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : TokenRepository {

    override var refreshToken: String
        set(value) = sharedPreferencesManager.setString(REFRESH_TOKEN, value)
        get() = sharedPreferencesManager.getString(REFRESH_TOKEN, "")

    override var accessToken: String
        set(value) = sharedPreferencesManager.setString(ACCESS_TOKEN, value)
        get() = sharedPreferencesManager.getString(ACCESS_TOKEN, "")

    private val _isRefreshTokenInvalid: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isRefreshTokenInvalid: StateFlow<Boolean> = _isRefreshTokenInvalid.asStateFlow()

    override suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken> {
        randomShortDelay()
        return if (refreshToken.isEmpty()) {
            Result.failure(
                ServerException("MOCK_ERROR", "refreshToken 이 만료되었습니다.")
            )
        } else {
            Result.success(
                JwtToken(
                    accessToken = "mock_access_token",
                    refreshToken = "mock_refresh_token"
                )
            )
        }.onSuccess { token ->
            this.refreshToken = token.refreshToken
            this.accessToken = token.accessToken
            _isRefreshTokenInvalid.value = false
        }.onFailure { exception ->
            _isRefreshTokenInvalid.value = true
        }.map { token ->
            JwtToken(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
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
    }
}
