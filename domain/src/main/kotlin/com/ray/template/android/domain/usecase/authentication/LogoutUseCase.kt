package com.ray.template.android.domain.usecase.authentication

import com.ray.template.android.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authenticationRepository.logout()
    }
}
