package com.ray.template.android.domain.repository

import com.ray.template.android.domain.model.user.Profile

interface UserRepository {

    suspend fun getProfile(): Result<Profile>
}
