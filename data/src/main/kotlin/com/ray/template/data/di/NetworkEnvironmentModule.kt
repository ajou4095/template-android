package com.ray.template.data.di

import com.ray.template.data.remote.local.SharedPreferencesManager
import com.ray.template.data.remote.network.environment.BaseUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkEnvironmentModule {

    @Provides
    @Singleton
    fun provideBaseUrlProvider(
        sharedPreferencesManager: SharedPreferencesManager
    ): BaseUrlProvider {
        return BaseUrlProvider(sharedPreferencesManager)
    }
}
