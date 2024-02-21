package com.ray.template.android.domain.repository

import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.domain.model.authentication.JwtToken

interface TokenRepository {

    var refreshToken: String

    var accessToken: String

    val refreshFailEvent: EventFlow<Unit>

    suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken>
}
