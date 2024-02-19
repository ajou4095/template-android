package com.ray.template.data.remote.local.database

import com.ray.template.data.remote.local.database.sample.SampleTable
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class BookkeepingDatabase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sampleTable: SampleTable
) {
    init {
        transaction(
            Database.connect(
                String.format(DATABASE_PATH, context.packageName),
                DRIVER_CLASS_NAME
            )
        ) {
            SchemaUtils.create(sampleTable)
        }
    }

    suspend fun <T> dbQuery(
        block: suspend () -> T
    ): T {
        return newSuspendedTransaction(Dispatchers.IO) { block() }
    }

    companion object {
        private const val DRIVER_CLASS_NAME = "org.h2.Driver"
        private const val DATABASE_PATH = "jdbc:h2:file:./data/data/%s/databases/sample"
    }
}