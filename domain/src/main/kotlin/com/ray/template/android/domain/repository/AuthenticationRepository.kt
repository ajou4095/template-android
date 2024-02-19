package com.ray.template.android.domain.repository

import com.ray.template.android.domain.model.authentication.JwtToken

interface AuthenticationRepository {

    var refreshToken: String

    var accessToken: String

    suspend fun refreshToken(
        refreshToken: String
    ): Result<JwtToken>

    // TODO : password encrypt
    suspend fun login(
        username: String,
        password: String
    ): Result<Long>

    suspend fun logout(): Result<Unit>

    suspend fun register(
        username: String,
        password: String
    ): Result<Long>

    suspend fun withdraw(): Result<Unit>
}
