package com.ray.template.data.remote.local.database.sample

import com.ray.template.data.remote.local.database.BookkeepingDatabase
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import javax.inject.Inject

class SampleDao @Inject constructor(
    private val database: BookkeepingDatabase,
    private val sampleTable: SampleTable
) {

    suspend fun select(id: Long): SampleEntity? {
        return database.dbQuery {
            sampleTable.select { sampleTable.id eq id }
                .limit(1)
                .map(::mapSample)
                .singleOrNull()
        }
    }

    suspend fun selectAll(): List<SampleEntity> {
        return database.dbQuery {
            sampleTable.selectAll()
                .orderBy(sampleTable.id to SortOrder.ASC)
                .map(::mapSample)
        }
    }

    suspend fun selectRandom(): SampleEntity? {
        return database.dbQuery {
            sampleTable
                .selectAll()
                .orderBy(Random())
                .limit(1)
                .map(::mapSample)
                .singleOrNull()
        }
    }

    suspend fun insert(sample: SampleEntity) {
        return database.dbQuery {
            sampleTable.insert {
                it[sampleTable.id] = sample.id
            }
        }
    }

    suspend fun insertAll(cities: List<SampleEntity>) {
        return database.dbQuery {
            cities.forEach { sample ->
                sampleTable.insert {
                    it[sampleTable.id] = sample.id
                }
            }
        }
    }

    private fun mapSample(row: ResultRow): SampleEntity {
        return SampleEntity(
            id = row[sampleTable.id]
        )
    }
}
