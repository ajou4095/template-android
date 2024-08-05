package com.ray.template.android.data.remote.network.api

import com.ray.template.android.data.remote.network.model.nonfeature.error.ErrorRes
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface ApiRoute {
    val defaultHeaders: Headers
        get() = headersOf(HttpHeaders.ContentType, "application/json")

    val MockRequestHandleScope.defaultSuccess
        get() = respond(
            content = ByteReadChannel("""{}"""),
            status = HttpStatusCode.OK,
            headers = defaultHeaders
        )

    val MockRequestHandleScope.defaultError
        get() = respond(
            content = ByteReadChannel(
                Json.encodeToString(
                    ErrorRes(
                        id = ApiTestConstant.DEFAULT_ERROR_ID,
                        message = ApiTestConstant.DEFAULT_ERROR_MESSAGE
                    )
                )
            ),
            status = HttpStatusCode.BadRequest,
            headers = defaultHeaders
        )

    fun MockRequestHandleScope.routing(
        request: HttpRequestData
    ): HttpResponseData?
}
