package com.ray.template.android.presentation.ui.main.nonlogin.onboarding

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class OnBoardingArgument(
    val state: OnBoardingState,
    val event: EventFlow<OnBoardingEvent>,
    val intent: (OnBoardingIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface OnBoardingState {
    data object Init : OnBoardingState
}


sealed interface OnBoardingEvent

sealed interface OnBoardingIntent
