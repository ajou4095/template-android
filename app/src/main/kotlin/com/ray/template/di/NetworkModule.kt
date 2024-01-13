package com.ray.template.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val contentType = "application/json".toMediaType()

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val isDebug: Boolean = (0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)

        return OkHttpClient.Builder()
            .apply {
                if (isDebug) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }.build()
    }

    @Provides
    @Singleton
    fun provideCommonRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(MUSIC_MATCH_BASE_URL)
            .client(client)
            .build()
    }

    companion object {
        // TODO : Network Interceptor 로 동적으로 Dev / Release Domain 변경
        private const val MUSIC_MATCH_BASE_URL = "https://api.musixmatch.com/"
    }
}
