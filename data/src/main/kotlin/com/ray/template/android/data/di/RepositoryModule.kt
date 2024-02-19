package com.ray.template.android.data.di

import com.ray.template.android.data.repository.sample.MockSampleRepository
import com.ray.template.android.data.repository.authentication.MockAuthenticationRepository
import com.ray.template.android.domain.repository.AuthenticationRepository
import com.ray.template.android.domain.repository.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsSampleRepository(
        mockSampleRepository: MockSampleRepository
    ): SampleRepository

    @Binds
    @Singleton
    abstract fun bindsAuthenticationRepository(
        authenticationRepository: MockAuthenticationRepository
    ): AuthenticationRepository
}
