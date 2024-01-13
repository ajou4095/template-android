package com.ray.template.domain.repository

interface AuthenticationRepository {

    var refreshToken: String

    var accessToken: String

    suspend fun getAccessToken(
        refreshToken: String
    ): Result<String>
}
