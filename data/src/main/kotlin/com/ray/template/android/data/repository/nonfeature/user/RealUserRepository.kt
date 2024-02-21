package com.ray.template.android.data.repository.nonfeature.user

import com.ray.template.android.data.remote.network.api.nonfeature.UserApi
import com.ray.template.android.data.remote.network.util.toDomain
import com.ray.template.android.domain.model.nonfeature.user.Profile
import com.ray.template.android.domain.repository.nonfeature.UserRepository
import javax.inject.Inject

class RealUserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getProfile(): Result<Profile> {
        return userApi.getProfile().toDomain()
    }
}
