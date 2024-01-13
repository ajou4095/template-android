package com.ray.template.data.remote.network.api

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/ws/1.1")
class SampleApi {
    @Resource("/matcher.lyrics.get")
    class GetLyrics(
        val parent: SampleApi = SampleApi(),
        @SerialName("apikey") val apiKey: String,
        @SerialName("q_track") val title: String,
        @SerialName("q_artist") val artist: String
    )
}
