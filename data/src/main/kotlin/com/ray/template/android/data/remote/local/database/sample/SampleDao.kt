package com.ray.template.android.data.remote.local.database.sample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg users: SampleEntity)

    @Query("SELECT * FROM sample WHERE id = :id LIMIT 1")
    suspend fun get(id: Int): SampleEntity

    @Query("SELECT * FROM sample")
    suspend fun getAll(): List<SampleEntity>

    @Update
    suspend fun update(vararg users: SampleEntity)

    @Delete
    suspend fun delete(vararg users: SampleEntity)
}
