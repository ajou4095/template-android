package com.ray.template.android.data.remote.network.model.nonfeature.user

import com.ray.template.android.data.remote.mapper.DataMapper
import com.ray.template.android.domain.model.nonfeature.user.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRes(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("email")
    val email: String
) : DataMapper<Profile> {
    override fun toDomain(): Profile {
        return Profile(
            id = id,
            name = name,
            nickname = nickname,
            email = email
        )
    }
}
