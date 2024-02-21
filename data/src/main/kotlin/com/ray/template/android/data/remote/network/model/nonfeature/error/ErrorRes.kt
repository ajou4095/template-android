package com.ray.template.android.data.remote.network.model.nonfeature.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorRes(
    @SerialName("id")
    val id: String,
    @SerialName("message")
    val message: String
)
