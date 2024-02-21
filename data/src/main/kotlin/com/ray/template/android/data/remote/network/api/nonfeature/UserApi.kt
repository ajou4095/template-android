package com.ray.template.android.data.remote.network.api.nonfeature

import com.ray.template.android.data.remote.network.di.AuthHttpClient
import com.ray.template.android.data.remote.network.environment.BaseUrlProvider
import com.ray.template.android.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.android.data.remote.network.model.nonfeature.user.ProfileRes
import com.ray.template.android.data.remote.network.util.convert
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class UserApi @Inject constructor(
    @AuthHttpClient private val client: HttpClient,
    private val baseUrlProvider: BaseUrlProvider,
    private val errorMessageMapper: ErrorMessageMapper
) {
    private val baseUrl: String
        get() = baseUrlProvider.get()

    suspend fun getProfile(): Result<ProfileRes> {
        return client.get("$baseUrl/api/v1/user/profile")
            .convert(errorMessageMapper::map)
    }
}
