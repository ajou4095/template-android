package com.ray.template.domain.repository

import com.ray.template.domain.model.SampleInformation
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Flow<SampleInformation>
}