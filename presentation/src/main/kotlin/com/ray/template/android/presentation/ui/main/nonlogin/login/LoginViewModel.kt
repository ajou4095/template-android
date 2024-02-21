package com.ray.template.android.presentation.ui.main.nonlogin.login

import androidx.lifecycle.SavedStateHandle
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.usecase.nonfeature.authentication.LoginUseCase
import com.ray.template.android.presentation.common.base.BaseViewModel
import com.ray.template.android.presentation.common.base.ErrorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event: MutableEventFlow<LoginEvent> = MutableEventFlow()
    val event: EventFlow<LoginEvent> = _event.asEventFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnConfirm -> {
                login()
            }
        }
    }

    private fun login() {
        launch {
            _state.value = LoginState.Loading

            loginUseCase(
                username = "username",
                password = "password"
            ).onSuccess {
                _event.emit(LoginEvent.Login.Success)
            }.onFailure { exception ->
                _state.value = LoginState.Init
                when (exception) {
                    is ServerException -> {
                        _errorEvent.emit(ErrorEvent.InvalidRequest(exception))
                    }

                    else -> {
                        _errorEvent.emit(ErrorEvent.UnavailableServer(exception))
                    }
                }
            }
        }
    }
}
