package com.ray.template.android.domain.usecase.user

import com.ray.template.android.domain.model.user.Profile
import com.ray.template.android.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Profile> {
        return userRepository.getProfile()
    }
}
