package com.ray.template.android.data.remote.network.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReq(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)