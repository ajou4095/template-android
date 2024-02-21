package com.ray.template.android.presentation.ui.main.home

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class HomeArgument(
    val state: HomeState,
    val event: EventFlow<HomeEvent>,
    val intent: (HomeIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface HomeState {
    data object Init : HomeState
}


sealed interface HomeEvent

sealed interface HomeIntent
