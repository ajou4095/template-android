package com.ray.template.android.data.remote.network.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
        @ApplicationContext context: Context,
        dataStore: DataStore<Preferences>
    ): BaseUrlProvider {
        return BaseUrlProvider(context, dataStore)
    }

    @Provides
    @Singleton
    fun provideErrorMessageMapper(
        @ApplicationContext context: Context
    ): ErrorMessageMapper {
        return ErrorMessageMapper(context)
    }
}
