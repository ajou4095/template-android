package com.ray.template.android.data.remote.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.ray.template.android.data.remote.local.database.TemplateDatabase
import com.ray.template.android.data.remote.local.database.sample.SampleDao
import com.ray.template.android.data.remote.local.preferences.PreferencesConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import kotlinx.coroutines.test.TestScope

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LocalModule::class]
)
object LocalModuleTest {
    @Provides
    @Singleton
    internal fun provideDataStore(
        @ApplicationContext context: Context,
        scope: TestScope
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            scope = scope,
            produceFile =
            { context.preferencesDataStoreFile(PreferencesConstant.PREFERENCES_NAME) }
        )
    }

    @Provides
    @Singleton
    internal fun provideTemplateDatabase(
        @ApplicationContext context: Context
    ): TemplateDatabase {
        return Room.databaseBuilder(
            context,
            TemplateDatabase::class.java,
            TemplateDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    internal fun provideSampleDao(
        templateDatabase: TemplateDatabase
    ): SampleDao {
        return templateDatabase.sampleDao()
    }
}
