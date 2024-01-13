package com.ray.template.data.remote.network.api

import com.ray.template.data.remote.network.di.AuthHttpClient
import com.ray.template.data.remote.network.environment.BaseUrlProvider
import com.ray.template.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.data.remote.network.model.sample.SampleInformationRes
import com.ray.template.data.remote.network.util.convert
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SampleApi(
    @AuthHttpClient private val client: HttpClient,
    private val baseUrlProvider: BaseUrlProvider,
    private val errorMessageMapper: ErrorMessageMapper
) {
    private val baseUrl: String
        get() = baseUrlProvider.get()

    suspend fun getLyrics(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformationRes> {
        return client.get("$baseUrl/ws/1.1") {
            parameter("api_key", apiKey)
            parameter("q_track", title)
            parameter("q_artist", artist)
        }.convert(errorMessageMapper::map)
    }
}
