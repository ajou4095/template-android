package com.ray.template.android.data.remote.network.api.nonfeature

import com.ray.template.android.data.remote.network.di.NoAuthHttpClient
import com.ray.template.android.data.remote.network.environment.BaseUrlProvider
import com.ray.template.android.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.android.data.remote.network.model.nonfeature.authentication.GetAccessTokenRes
import com.ray.template.android.data.remote.network.util.convert
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import javax.inject.Inject

class TokenApi @Inject constructor(
    @NoAuthHttpClient private val noAuthClient: HttpClient,
    private val baseUrlProvider: BaseUrlProvider,
    private val errorMessageMapper: ErrorMessageMapper
) {
    private val baseUrl: String
        get() = baseUrlProvider.get()

    suspend fun getAccessToken(
        refreshToken: String
    ): Result<GetAccessTokenRes> {
        return noAuthClient.post("$baseUrl/api/v1/auth/refresh") {
            header("Token-Refresh", refreshToken)
        }.convert(errorMessageMapper::map)
    }
}
