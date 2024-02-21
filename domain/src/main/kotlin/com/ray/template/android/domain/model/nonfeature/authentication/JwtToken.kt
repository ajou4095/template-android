package com.ray.template.android.domain.model.nonfeature.authentication

data class JwtToken(
    val accessToken: String,
    val refreshToken: String
)
