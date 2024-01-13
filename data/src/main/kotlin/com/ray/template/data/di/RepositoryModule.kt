package com.ray.template.data.di

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.repository.MockSampleRepository
import com.ray.template.domain.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Provides
    @Singleton
    fun bindsSampleRepository(
        sampleApi: SampleApi
    ): SampleRepository {
        // return RealSampleRepository(sampleApi)
        return MockSampleRepository()
    }
}
