package com.ray.template.android.data.remote.network.di

import android.content.Context
import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.data.remote.network.environment.BaseUrlProvider
import com.ray.template.android.data.remote.network.environment.ErrorMessageMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideErrorMessageMapper(
        @ApplicationContext context: Context
    ): ErrorMessageMapper {
        return ErrorMessageMapper(context)
    }
}
