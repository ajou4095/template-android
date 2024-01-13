package com.ray.template.data.di

import com.ray.template.data.remote.network.api.SampleApi
import com.ray.template.data.repository.MockSampleRepository
import com.ray.template.domain.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// TODO : object, abstract class, interface 중 어떤 것을 사용해야 하는지 찾기
@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Provides
    @Singleton
    fun bindsSampleRepository(
        sampleApi: SampleApi
    ): SampleRepository {
        // TODO : Mocking 동적으로 변경하는 방법 찾기
        // Provide 할 때 Flag 로 변경하기??
        // return RealSampleRepository()
        return MockSampleRepository()
    }
}