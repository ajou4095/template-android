package com.ray.template.android.domain.usecase.nonfeature.authentication

import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authenticationRepository.logout()
    }
}
