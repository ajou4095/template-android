package com.ray.template.android.data.repository.nonfeature.authentication.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.domain.model.nonfeature.authentication.JwtToken
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MockTokenRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenRepository {

    private val _refreshFailEvent: MutableEventFlow<Unit> = MutableEventFlow()
    override val refreshFailEvent: EventFlow<Unit> = _refreshFailEvent.asEventFlow()

    override suspend fun login(
        username: String,
        password: String
    ): Result<Long> {
        randomShortDelay()
        return Result.success(0L).map { token ->
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(REFRESH_TOKEN)] = "mock_refresh_token"
                preferences[stringPreferencesKey(ACCESS_TOKEN)] = "mock_access_token"
            }
            token
        }
    }

    override suspend fun register(
        username: String,
        password: String
    ): Result<Long> {
        randomLongDelay()
        return Result.success(0L).map { register ->
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(REFRESH_TOKEN)] = "mock_refresh_token"
                preferences[stringPreferencesKey(ACCESS_TOKEN)] = "mock_access_token"
            }
            register
        }
    }

    override suspend fun getRefreshToken(): String {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(REFRESH_TOKEN)]
        }.first().orEmpty()

    }

    override suspend fun getAccessToken(): String {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(ACCESS_TOKEN)]
        }.first().orEmpty()
    }

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
            ).map { token ->
                dataStore.edit { preferences ->
                    preferences[stringPreferencesKey(REFRESH_TOKEN)] = token.refreshToken
                    preferences[stringPreferencesKey(ACCESS_TOKEN)] = token.accessToken
                }
                JwtToken(
                    accessToken = token.accessToken,
                    refreshToken = token.refreshToken
                )
            }.onFailure { exception ->
                _refreshFailEvent.emit(Unit)
            }
        }
    }

    override suspend fun removeToken(): Result<Unit> {
        // TODO : KTOR-4759 BearerAuthProvider caches result of loadToken until process death
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(REFRESH_TOKEN))
            preferences.remove(stringPreferencesKey(ACCESS_TOKEN))
        }
        return Result.success(Unit)
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
