package com.ray.template.android.presentation.ui.main.common.gallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ray.template.android.presentation.model.gallery.GalleryImage

class GalleryPagingSource(
    private val galleryCursor: GalleryCursor,
    private val currentLocation: String?
) : PagingSource<Int, GalleryImage>() {

    override fun getRefreshKey(state: PagingState<Int, GalleryImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryImage> {
        return try {
            val position = params.key ?: STARTING_PAGE_IDX
            val data = galleryCursor.getPhotoList(
                page = position,
                loadSize = params.loadSize,
                currentLocation = currentLocation
            )
            val endOfPaginationReached = data.isEmpty()
            val prevKey = if (position == STARTING_PAGE_IDX) null else position - 1
            val nextKey =
                if (endOfPaginationReached) null else position + (params.loadSize / PAGING_SIZE)
            LoadResult.Page(data, prevKey, nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_PAGE_IDX = 1
        const val PAGING_SIZE = 30
    }
}
