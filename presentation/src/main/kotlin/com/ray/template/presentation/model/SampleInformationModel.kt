package com.ray.template.presentation.model

import android.os.Parcelable
import com.ray.template.domain.model.SampleInformation
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleInformationModel(
    val lyricsId: Int = 0,
    val lyricsBody: String = ""
) : Parcelable

// TODO : 개선 방법 찾기
fun SampleInformation.toUiModel(): SampleInformationModel {
    return SampleInformationModel(lyricsId, lyricsBody)
}
