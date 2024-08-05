package com.ray.template.android.presentation.ui.main.home.mypage

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlin.coroutines.CoroutineContext

@Immutable
data class MyPageArgument(
    val state: MyPageState,
    val event: EventFlow<MyPageEvent>,
    val intent: (MyPageIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val coroutineContext: CoroutineContext
)

sealed interface MyPageState {
    data object Init : MyPageState
    data object Loading : MyPageState
}


sealed interface MyPageEvent

sealed interface MyPageIntent
