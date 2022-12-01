package com.ray.template.data.remote.network.api

import com.ray.template.data.remote.network.model.SampleInformationRes
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SampleApi {
    // TODO : Network Interceptor 로 apiKey 집어넣기
    @GET("/ws/1.1/matcher.lyrics.get")
    fun getSampleInformation(
        @Query("apikey") apiKey: String,
        @Query("q_track") title: String,
        @Query("q_artist") artist: String
    ): Flow<Response<SampleInformationRes>>
}