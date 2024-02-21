package com.ray.template.android.data.repository.user

import com.ray.template.android.data.remote.network.api.UserApi
import com.ray.template.android.data.remote.network.util.toDomain
import com.ray.template.android.domain.model.user.Profile
import com.ray.template.android.domain.repository.UserRepository
import javax.inject.Inject

class RealUserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getProfile(): Result<Profile> {
        return userApi.getProfile().toDomain()
    }
}
