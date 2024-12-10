package com.ray.template.android.data.repository.nonfeature.authentication.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.data.remote.network.api.nonfeature.TokenApi
import com.ray.template.android.domain.model.nonfeature.authentication.JwtToken
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class RealTokenRepository @Inject constructor(
    private val tokenApi: TokenApi,
    private val dataStore: DataStore<Preferences>
) : TokenRepository {

    private val _refreshFailEvent: MutableEventFlow<Unit> = MutableEventFlow()
    override val refreshFailEvent: EventFlow<Unit> = _refreshFailEvent.asEventFlow()

    override suspend fun login(
        username: String,
        password: String
    ): Result<Long> {
        return tokenApi.login(
            username = username,
            password = password,
        ).map { token ->
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(REFRESH_TOKEN)] = token.refreshToken
                preferences[stringPreferencesKey(ACCESS_TOKEN)] = token.accessToken
            }
            token.id
        }
    }

    override suspend fun register(
        username: String,
        password: String
    ): Result<Long> {
        return tokenApi.register(
            username = username,
            password = password
        ).map { register ->
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(REFRESH_TOKEN)] = register.refreshToken
                preferences[stringPreferencesKey(ACCESS_TOKEN)] = register.accessToken
            }
            register.id
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
        return if (refreshToken.isEmpty()) {
            // TODO : 적절한 Exception 이름
            Result.failure(ServerException("Client Error", "refreshToken is empty."))
        } else {
            tokenApi.getAccessToken(
                refreshToken = refreshToken
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
                removeToken()
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

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN = "access_token"
    }
}
