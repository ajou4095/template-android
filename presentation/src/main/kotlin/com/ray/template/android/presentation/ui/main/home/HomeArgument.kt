package com.ray.template.android.presentation.ui.main.home

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlin.coroutines.CoroutineContext

@Immutable
data class HomeArgument(
    val state: HomeState,
    val event: EventFlow<HomeEvent>,
    val intent: (HomeIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val coroutineContext: CoroutineContext
)

sealed interface HomeState {
    data object Init : HomeState
}


sealed interface HomeEvent

sealed interface HomeIntent
