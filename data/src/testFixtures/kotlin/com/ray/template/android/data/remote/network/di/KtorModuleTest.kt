package com.ray.template.android.data.remote.network.di

import android.content.Context
import com.ray.template.android.data.remote.network.api.ApiTestConstant
import com.ray.template.android.data.remote.network.api.nonfeature.FileApiTestRoute
import com.ray.template.android.data.remote.network.model.nonfeature.error.ErrorRes
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import javax.inject.Singleton
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [KtorModule::class]
)
internal object KtorModuleTest {

    private val jsonConfiguration = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val mockEngine = MockEngine { request ->
        listOf(
            FileApiTestRoute()
        ).forEach { route ->
            with(route) {
                routing(request)?.let { response ->
                    return@MockEngine response
                }
            }
        }

        return@MockEngine respond(
            content = ByteReadChannel(
                Json.encodeToString(
                    ErrorRes(
                        id = ApiTestConstant.DEFAULT_ERROR_ID,
                        message = ApiTestConstant.DEFAULT_ERROR_MESSAGE
                    )
                )
            ),
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    @Provides
    @Singleton
    @NoAuthHttpClient
    fun provideNoAuthHttpClient(
        @ApplicationContext context: Context
    ): HttpClient {
        return HttpClient(mockEngine) {
            expectSuccess = false

            install(ContentNegotiation) {
                json(jsonConfiguration)
            }

            defaultRequest {
                header("Content-Type", "application/json")
            }
        }
    }

    @Provides
    @Singleton
    @AuthHttpClient
    fun provideAuthHttpClient(
        @ApplicationContext context: Context,
        tokenRepository: TokenRepository
    ): HttpClient {
        return HttpClient(mockEngine) {
            expectSuccess = false

            install(ContentNegotiation) {
                json(jsonConfiguration)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = tokenRepository.getAccessToken()
                        val refreshToken = tokenRepository.getRefreshToken()
                        if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                            return@loadTokens null
                        }

                        BearerTokens(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    }

                    refreshTokens {
                        val refreshToken = tokenRepository.getRefreshToken()
                        if (refreshToken.isEmpty()) {
                            return@refreshTokens null
                        }

                        tokenRepository.refreshToken(
                            refreshToken
                        ).getOrNull()?.let { token ->
                            BearerTokens(
                                accessToken = token.accessToken,
                                refreshToken = token.refreshToken
                            )
                        }
                    }
                }
            }

            defaultRequest {
                header("Content-Type", "application/json")
            }
        }
    }
}
