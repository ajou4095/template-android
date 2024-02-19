package com.ray.template.android.data.remote.local.di

import android.content.Context
import androidx.room.Room
import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.data.remote.local.database.TemplateDatabase
import com.ray.template.android.data.remote.local.database.sample.SampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ): SharedPreferencesManager {
        return SharedPreferencesManager(context)
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
