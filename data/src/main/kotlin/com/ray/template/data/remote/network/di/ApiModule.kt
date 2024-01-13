package com.ray.template.data.remote.network.di

import com.ray.template.data.remote.network.environment.ErrorMessageMapper
import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.remote.network.environment.BaseUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideSampleApi(
        client: HttpClient,
        baseUrlProvider: BaseUrlProvider,
        errorMessageMapper: ErrorMessageMapper
    ): SampleApi {
        return SampleApi(
            client,
            baseUrlProvider,
            errorMessageMapper
        )
    }
}
