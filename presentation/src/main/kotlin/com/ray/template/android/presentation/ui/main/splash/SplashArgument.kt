package com.ray.template.android.presentation.ui.main.splash

import androidx.compose.runtime.Immutable
import com.ray.template.android.presentation.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class SplashArgument(
    val state: SplashState,
    val event: EventFlow<SplashEvent>,
    val intent: (SplashIntent) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface SplashState {
    data object Init : SplashState
    data object Loading : SplashState
}


sealed interface SplashEvent {
    sealed interface Login : SplashEvent {
        data object Success : Login
    }
}

sealed interface SplashIntent
