package com.ray.template.di

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.ray.template.BuildConfig
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideFlipperClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                FlipperOkhttpInterceptor(
                    AndroidFlipperClient.getInstance(context).getPlugin(NetworkFlipperPlugin.ID)
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCommonRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .baseUrl(MUSIC_MATCH_BASE_URL).apply {
                if (BuildConfig.DEBUG) client(client)
            }.build()
    }

    companion object {
        private const val MUSIC_MATCH_BASE_URL = "https://api.musixmatch.com/"
    }
}