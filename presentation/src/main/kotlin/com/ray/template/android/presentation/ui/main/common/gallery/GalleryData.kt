package com.ray.template.android.presentation.ui.main.common.gallery

import androidx.compose.runtime.Immutable
import androidx.paging.compose.LazyPagingItems
import com.ray.template.android.presentation.model.gallery.GalleryFolder
import com.ray.template.android.presentation.model.gallery.GalleryImage

@Immutable
data class GalleryData(
    val folderList: List<GalleryFolder>,
    val galleryImageList: LazyPagingItems<GalleryImage>
)
