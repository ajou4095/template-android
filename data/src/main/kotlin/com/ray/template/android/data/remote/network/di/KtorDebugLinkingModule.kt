package com.ray.template.android.data.remote.network.di

import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkEnvironmentDebugModule {

    @BindsOptionalOf
    @DebugInterceptor
    abstract fun bindsDebugInterceptor(): Interceptor
}

@Qualifier
annotation class DebugInterceptor
