package com.ray.template.android.data.remote.network.model.nonfeature.authentication

import com.ray.template.android.data.remote.mapper.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRes(
    @SerialName("id")
    val id: Long,
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
) : DataMapper<Long> {
    override fun toDomain(): Long {
        return id
    }
}
