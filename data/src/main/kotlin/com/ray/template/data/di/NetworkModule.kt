package com.ray.template.data.di

import android.content.Context
import android.content.pm.ApplicationInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // TODO : Network Interceptor 로 동적으로 Dev / Release Domain 변경
    private const val MUSIC_MATCH_BASE_URL = "https://api.musixmatch.com/"

    private val jsonConfiguration = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context
    ): HttpClient {
        val isDebug: Boolean = (0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)

        return HttpClient(OkHttp) {
            // default validation to throw exceptions for non-2xx responses
            expectSuccess = true

            engine {
                if (isDebug) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }

            defaultRequest {
                url(MUSIC_MATCH_BASE_URL)
            }

            install(ContentNegotiation) {
                json(jsonConfiguration)
            }
            install(Resources)
        }
    }
}
