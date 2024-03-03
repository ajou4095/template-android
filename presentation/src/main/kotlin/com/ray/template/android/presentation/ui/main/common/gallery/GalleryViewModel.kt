package com.ray.template.android.presentation.ui.main.common.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.presentation.common.base.BaseViewModel
import com.ray.template.android.presentation.model.gallery.GalleryFolder
import com.ray.template.android.presentation.model.gallery.GalleryImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val galleryCursor: GalleryCursor
) : BaseViewModel() {

    private val _state: MutableStateFlow<GalleryState> = MutableStateFlow(GalleryState.Init)
    val state: StateFlow<GalleryState> = _state.asStateFlow()

    private val _event: MutableEventFlow<GalleryEvent> = MutableEventFlow()
    val event: EventFlow<GalleryEvent> = _event.asEventFlow()

    private val _galleryImageList = MutableStateFlow<PagingData<GalleryImage>>(PagingData.empty())
    val galleryImageList: StateFlow<PagingData<GalleryImage>> = _galleryImageList.asStateFlow()

    private val _folderList: MutableStateFlow<List<GalleryFolder>> =
        MutableStateFlow(listOf(GalleryFolder.recent))
    val folderList: StateFlow<List<GalleryFolder>> = _folderList.asStateFlow()

    fun onIntent(intent: GalleryIntent) {
        when (intent) {
            is GalleryIntent.OnGrantPermission -> {
                _folderList.value = galleryCursor.getFolderList()
                getGalleryPagingImages(GalleryFolder.recent)
            }

            is GalleryIntent.OnChangeFolder -> {
                getGalleryPagingImages(intent.folder)
            }
        }
    }

    private fun getGalleryPagingImages(
        folder: GalleryFolder
    ) {
        launch {
            Pager(
                config = PagingConfig(
                    pageSize = GalleryPagingSource.PAGING_SIZE,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = {
                    GalleryPagingSource(
                        galleryCursor = galleryCursor,
                        currentLocation = folder.location,
                    )
                },
            ).flow
                .cachedIn(viewModelScope)
                .collect {
                    _galleryImageList.value = it
                }
        }
    }
}
