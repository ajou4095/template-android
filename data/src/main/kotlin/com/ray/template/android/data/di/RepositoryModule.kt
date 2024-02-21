package com.ray.template.android.data.di

import com.ray.template.android.data.repository.nonfeature.authentication.MockAuthenticationRepository
import com.ray.template.android.data.repository.nonfeature.authentication.token.MockTokenRepository
import com.ray.template.android.data.repository.nonfeature.tracking.MockTrackingRepository
import com.ray.template.android.data.repository.nonfeature.user.MockUserRepository
import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import com.ray.template.android.domain.repository.nonfeature.TrackingRepository
import com.ray.template.android.domain.repository.nonfeature.UserRepository
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
    abstract fun bindsAuthenticationRepository(
        authenticationRepository: MockAuthenticationRepository
    ): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindsTokenRepository(
        tokenRepository: MockTokenRepository
    ): TokenRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepository: MockUserRepository
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindsTrackingRepository(
        userRepository: MockTrackingRepository
    ): TrackingRepository
}
