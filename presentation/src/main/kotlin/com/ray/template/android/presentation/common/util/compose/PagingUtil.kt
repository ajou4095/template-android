package com.ray.template.android.presentation.common.util.compose

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun LazyPagingItems<*>.isEmpty(): Boolean {
    return loadState.source.refresh is LoadState.NotLoading
            && loadState.append.endOfPaginationReached
            && itemCount <= 0
}
