package com.ray.template.android.data.remote.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ray.template.android.data.remote.local.database.sample.SampleDao
import com.ray.template.android.data.remote.local.database.sample.SampleEntity

@Database(entities = [SampleEntity::class], version = 1, exportSchema = false)
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    companion object {
        const val DATABASE_NAME = "template"
    }
}
