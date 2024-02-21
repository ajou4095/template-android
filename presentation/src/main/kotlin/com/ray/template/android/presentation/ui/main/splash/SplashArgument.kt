package com.ray.template.android.presentation.ui.main.splash

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class SplashArgument(
    val state: SplashState,
    val event: EventFlow<SplashEvent>,
    val intent: (SplashIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface SplashState {
    data object Init : SplashState
}


sealed interface SplashEvent {
    sealed interface Login : SplashEvent {
        data object Success : Login
        data object Fail : Login
    }
}

sealed interface SplashIntent
