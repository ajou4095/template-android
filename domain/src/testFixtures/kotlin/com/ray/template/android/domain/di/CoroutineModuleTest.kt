package com.ray.template.android.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineModule::class]
)
object CoroutineModuleTest {

    @Provides
    @Singleton
    @MainDispatcher
    internal fun provideMainDispatcher(): CoroutineDispatcher {
        return StandardTestDispatcher()
    }

    @Provides
    @Singleton
    @IODispatcher
    internal fun provideIODispatcher(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineDispatcher {
        return mainDispatcher
    }

    @Provides
    @Singleton
    @DefaultDispatcher
    internal fun provideDefaultDispatcher(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineDispatcher {
        return mainDispatcher
    }

    @Provides
    @Singleton
    @UnconfinedDispatcher
    internal fun provideUnconfinedDispatcher(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineDispatcher {
        return mainDispatcher
    }

    @Provides
    @Singleton
    internal fun provideTestScope(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return TestScope(mainDispatcher)
    }
}
