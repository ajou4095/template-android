package com.ray.template.data.repository.authentication

import com.ray.template.data.remote.local.SharedPreferencesManager
import com.ray.template.data.remote.network.api.AuthenticationApi
import com.ray.template.domain.repository.AuthenticationRepository
import javax.inject.Inject

class RealAuthenticationRepository @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AuthenticationRepository {

    override var refreshToken: String
        set(value) = sharedPreferencesManager.setString(REFRESH_TOKEN, value)
        get() = sharedPreferencesManager.getString(REFRESH_TOKEN, "")
    override var accessToken: String
        set(value) = sharedPreferencesManager.setString(ACCESS_TOKEN, value)
        get() = sharedPreferencesManager.getString(ACCESS_TOKEN, "")

    override suspend fun getAccessToken(
        refreshToken: String
    ): Result<String> {
        return authenticationApi.getAccessToken(
            refreshToken = refreshToken
        ).map {
            it.accessToken
        }
    }

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN = "access_token"
    }
}
