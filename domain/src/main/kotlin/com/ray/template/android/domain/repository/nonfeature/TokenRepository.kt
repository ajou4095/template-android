package com.ray.template.android.domain.repository.nonfeature

import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.domain.model.nonfeature.authentication.JwtToken

interface TokenRepository {

    val refreshFailEvent: EventFlow<Unit>

    // TODO : password encrypt
    suspend fun login(
        username: String,
        password: String
    ): Result<Long>

    suspend fun register(
        username: String,
        password: String
    ): Result<Long>

    suspend fun getRefreshToken(): String

    suspend fun getAccessToken(): String

    suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken>

    suspend fun removeToken(): Result<Unit>
}
