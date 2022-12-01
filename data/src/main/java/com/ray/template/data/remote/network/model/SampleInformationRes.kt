package com.ray.template.data.remote.network.model

import com.ray.template.data.mapper.DataMapper
import com.ray.template.domain.model.SampleInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SampleInformationRes(
    @SerialName("message")
    val message: LyricsResMessage = LyricsResMessage()
) : DataMapper<SampleInformation> {
    override fun toDomain(): SampleInformation {
        return SampleInformation(
            lyricsId = message.body.lyrics.lyricsId,
            lyricsBody = message.body.lyrics.lyricsBody
        )
    }
}

@Serializable
data class LyricsResMessage(
    @SerialName("body")
    val body: LyricsResBody = LyricsResBody()
)

@Serializable
data class LyricsResBody(
    @SerialName("lyrics")
    val lyrics: LyricsResLyrics = LyricsResLyrics()
)

@Serializable
data class LyricsResLyrics(
    @SerialName("lyrics_id")
    val lyricsId: Int = 0,

    @SerialName("lyrics_body")
    val lyricsBody: String = ""
)