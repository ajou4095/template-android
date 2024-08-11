package com.ray.template.android.data.remote.network.api.nonfeature

import com.ray.template.android.data.remote.network.di.AuthHttpClient
import com.ray.template.android.data.remote.network.environment.BaseUrlProvider
import com.ray.template.android.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.android.data.remote.network.util.convert
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.post
import javax.inject.Inject

class AuthenticationApi @Inject constructor(
    @AuthHttpClient private val client: HttpClient,
    private val baseUrlProvider: BaseUrlProvider,
    private val errorMessageMapper: ErrorMessageMapper
) {

    suspend fun logout(): Result<Unit> {
        return client.post("${baseUrlProvider.get()}/api/v1/auth/logout")
            .convert(errorMessageMapper::map)
    }

    suspend fun withdraw(): Result<Unit> {
        return client.delete("${baseUrlProvider.get()}/api/v1/auth/withdraw")
            .convert(errorMessageMapper::map)
    }
}
