package com.ray.template.android.domain.usecase.nonfeature.authentication

import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Result<Long> {
        return authenticationRepository.register(
            username = username,
            password = password
        )
    }
}
