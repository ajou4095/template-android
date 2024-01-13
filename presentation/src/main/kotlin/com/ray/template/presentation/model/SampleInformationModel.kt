package com.ray.template.presentation.model

import android.os.Parcelable
import com.ray.template.domain.model.sample.SampleInformation
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleInformationModel(
    val lyricsId: Int = 0,
    val lyricsBody: String = ""
) : Parcelable

fun SampleInformation.toUiModel(): SampleInformationModel {
    return SampleInformationModel(lyricsId, lyricsBody)
}
