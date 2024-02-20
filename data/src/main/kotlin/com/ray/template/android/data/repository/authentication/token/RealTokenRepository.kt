package com.ray.template.android.data.repository.authentication.token

import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.data.remote.network.api.TokenApi
import com.ray.template.android.domain.model.authentication.JwtToken
import com.ray.template.android.domain.repository.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RealTokenRepository @Inject constructor(
    private val tokenApi: TokenApi,
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
        return tokenApi.getAccessToken(
            refreshToken = refreshToken
        ).onSuccess { token ->
            this.refreshToken = token.refreshToken
            this.accessToken = token.accessToken
            _isRefreshTokenInvalid.value = false
        }.onFailure { exception ->
            // TODO : ID 체크
            _isRefreshTokenInvalid.value = true
        }.map { token ->
            JwtToken(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
        }
    }

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN = "access_token"
    }
}
