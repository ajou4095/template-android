package com.ray.template.presentation.ui.main.nonlogin.onboarding

import androidx.compose.runtime.Immutable
import com.ray.template.presentation.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class OnBoardingArgument(
    val state: OnBoardingState,
    val event: EventFlow<OnBoardingEvent>,
    val intent: (OnBoardingIntent) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface OnBoardingState {
    data object Init : OnBoardingState
    data object Loading : OnBoardingState
}


sealed interface OnBoardingEvent

sealed interface OnBoardingIntent
