package com.ray.template.android.domain.usecase.nonfeature.authentication.token

import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import javax.inject.Inject

class CheckRefreshTokenFilledUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): Boolean {
        return tokenRepository.refreshToken.isNotEmpty()
    }
}
