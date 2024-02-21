package com.ray.template.android.domain.repository.nonfeature

interface AuthenticationRepository {

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
