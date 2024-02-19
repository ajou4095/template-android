package com.ray.template.data.remote.local.database.sample

import org.jetbrains.exposed.sql.Table
import javax.inject.Inject

class SampleTable @Inject constructor() : Table() {

    val id = long("id").autoIncrement()

    override val primaryKey = PrimaryKey(id)
}
