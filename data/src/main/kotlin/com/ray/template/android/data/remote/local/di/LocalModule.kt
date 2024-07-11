package com.ray.template.android.data.remote.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.ray.template.android.data.remote.local.database.TemplateDatabase
import com.ray.template.android.data.remote.local.database.sample.SampleDao
import com.ray.template.android.data.remote.local.preferences.PreferencesConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PreferencesConstant.PREFERENCES_NAME
    )

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideTemplateDatabase(
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
    fun provideSampleDao(
        templateDatabase: TemplateDatabase
    ): SampleDao {
        return templateDatabase.sampleDao()
    }
}
