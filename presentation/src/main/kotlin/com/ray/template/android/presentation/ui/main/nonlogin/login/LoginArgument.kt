package com.ray.template.android.presentation.ui.main.nonlogin.login

import androidx.compose.runtime.Immutable
import com.ray.template.android.common.util.coroutine.event.EventFlow
import kotlinx.coroutines.CoroutineExceptionHandler

@Immutable
data class LoginArgument(
    val state: LoginState,
    val event: EventFlow<LoginEvent>,
    val intent: (LoginIntent) -> Unit,
    val logEvent: (eventName: String, params: Map<String, Any>) -> Unit,
    val handler: CoroutineExceptionHandler
)

sealed interface LoginState {
    data object Init : LoginState
    data object Loading : LoginState
}


sealed interface LoginEvent {
    sealed interface Login : LoginEvent {
        data object Success : Login
    }
}

sealed interface LoginIntent {
    data object OnConfirm : LoginIntent
}
