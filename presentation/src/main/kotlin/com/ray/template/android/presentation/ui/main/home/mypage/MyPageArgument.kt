package com.ray.template.android.presentation.ui.main.home.mypage

import androidx.compose.runtime.Immutable
import com.ray.template.android.presentation.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class MyPageArgument(
    val state: MyPageState,
    val event: EventFlow<MyPageEvent>,
    val intent: (MyPageIntent) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface MyPageState {
    data object Init : MyPageState
    data object Loading : MyPageState
}


sealed interface MyPageEvent

sealed interface MyPageIntent
