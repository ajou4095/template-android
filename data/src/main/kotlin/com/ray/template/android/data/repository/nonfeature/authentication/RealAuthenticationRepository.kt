package com.ray.template.android.data.repository.nonfeature.authentication

import com.ray.template.android.data.remote.network.api.nonfeature.AuthenticationApi
import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject

class RealAuthenticationRepository @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val tokenRepository: TokenRepository
) : AuthenticationRepository {

    override suspend fun logout(): Result<Unit> {
        return authenticationApi.logout()
            .onSuccess {
                tokenRepository.refreshToken = ""
                tokenRepository.accessToken = ""
            }
    }

    override suspend fun withdraw(): Result<Unit> {
        return authenticationApi.withdraw()
            .onSuccess {
                tokenRepository.refreshToken = ""
                tokenRepository.accessToken = ""
            }
    }
}
