package com.ray.template.android.data.remote.network.api.nonfeature

import com.ray.template.android.data.remote.network.api.ApiRoute
import com.ray.template.android.data.remote.network.api.ApiTestConstant
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json

class FileApiTestRoute : ApiRoute {

    override fun MockRequestHandleScope.routing(
        request: HttpRequestData
    ): HttpResponseData? {
        val method = request.method
        val domain = request.url.host
        val path = request.url.encodedPath

        return when {
            method == HttpMethod.Get && domain.endsWith(ApiTestConstant.SERVER_HOST) && path == "/v1/util/s3/url" -> {
                getPreSignedUrlList(request)
            }

            method == HttpMethod.Get && domain == ApiTestConstant.S3_HOST -> {
                upload(request)
            }

            else -> null
        }
    }

    private fun MockRequestHandleScope.getPreSignedUrlList(
        request: HttpRequestData
    ): HttpResponseData {
        val imageNum = request.url.parameters["imageNum"]?.toInt()
        val isSuccess = imageNum?.let { it >= 0 } == true

        return if (isSuccess) {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(

                    )
                ),
                status = HttpStatusCode.OK,
                headers = defaultHeaders
            )
        } else {
            defaultError
        }
    }

    private fun MockRequestHandleScope.upload(
        request: HttpRequestData
    ): HttpResponseData {
        return defaultSuccess
    }
}
