package com.ray.template.data.remote.network.api

import com.ray.template.data.remote.network.di.NoAuthHttpClient
import com.ray.template.data.remote.network.environment.BaseUrlProvider
import com.ray.template.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.data.remote.network.model.authentication.GetAccessTokenReq
import com.ray.template.data.remote.network.model.authentication.GetAccessTokenRes
import com.ray.template.data.remote.network.util.convert
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthenticationApi(
    @NoAuthHttpClient private val client: HttpClient,
    private val baseUrlProvider: BaseUrlProvider,
    private val errorMessageMapper: ErrorMessageMapper
) {
    private val baseUrl: String
        get() = baseUrlProvider.get()

    suspend fun getAccessToken(
        refreshToken: String
    ): Result<GetAccessTokenRes> {
        return client.post("$baseUrl/member/v1/refresh") {
            setBody(
                GetAccessTokenReq(
                    refreshToken = refreshToken
                )
            )
        }.convert(errorMessageMapper::map)
    }
}
