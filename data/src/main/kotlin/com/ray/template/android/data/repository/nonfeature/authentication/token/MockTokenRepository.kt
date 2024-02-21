package com.ray.template.android.data.repository.nonfeature.authentication.token

import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.domain.model.nonfeature.authentication.JwtToken
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockTokenRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : TokenRepository {

    override var refreshToken: String
        set(value) = sharedPreferencesManager.setString(REFRESH_TOKEN, value)
        get() = sharedPreferencesManager.getString(REFRESH_TOKEN, "")

    override var accessToken: String
        set(value) = sharedPreferencesManager.setString(ACCESS_TOKEN, value)
        get() = sharedPreferencesManager.getString(ACCESS_TOKEN, "")

    private val _refreshFailEvent: MutableEventFlow<Unit> = MutableEventFlow()
    override val refreshFailEvent: EventFlow<Unit> = _refreshFailEvent.asEventFlow()

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
            ).onSuccess { token ->
                this.refreshToken = token.refreshToken
                this.accessToken = token.accessToken
            }.onFailure { exception ->
                _refreshFailEvent.emit(Unit)
            }.map { token ->
                JwtToken(
                    accessToken = token.accessToken,
                    refreshToken = token.refreshToken
                )
            }
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
