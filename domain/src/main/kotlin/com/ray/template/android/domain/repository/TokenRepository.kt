package com.ray.template.android.domain.repository

import com.ray.template.android.domain.model.authentication.JwtToken
import kotlinx.coroutines.flow.StateFlow

interface TokenRepository {

    var refreshToken: String

    var accessToken: String

    val isRefreshTokenInvalid: StateFlow<Boolean>

    suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken>

    suspend fun resetRefreshTokenInvalidFlag()
}
