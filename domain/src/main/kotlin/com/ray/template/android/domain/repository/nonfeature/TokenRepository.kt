package com.ray.template.android.domain.repository.nonfeature

import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.domain.model.nonfeature.authentication.JwtToken

interface TokenRepository {

    var refreshToken: String

    var accessToken: String

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

    suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken>
}
