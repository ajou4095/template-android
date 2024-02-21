package com.ray.template.android.domain.repository.nonfeature

import com.ray.template.android.domain.model.nonfeature.user.Profile

interface UserRepository {

    suspend fun getProfile(): Result<Profile>
}
