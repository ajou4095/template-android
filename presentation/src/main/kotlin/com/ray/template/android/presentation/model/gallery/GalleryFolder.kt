package com.ray.template.android.presentation.model.gallery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryFolder(
    val name: String,
    val location: String?,
) : Parcelable {
    companion object {
        val recent = GalleryFolder("최근 항목", "")
    }
}
