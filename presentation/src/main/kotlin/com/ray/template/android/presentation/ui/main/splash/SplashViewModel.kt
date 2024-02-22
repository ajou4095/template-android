package com.ray.template.android.presentation.ui.main.splash

import androidx.lifecycle.SavedStateHandle
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.domain.usecase.nonfeature.authentication.token.CheckRefreshTokenFilledUseCase
import com.ray.template.android.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val checkRefreshTokenFilledUseCase: CheckRefreshTokenFilledUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Init)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _event: MutableEventFlow<SplashEvent> = MutableEventFlow()
    val event: EventFlow<SplashEvent> = _event.asEventFlow()

    init {
        launch {
            login()
        }
    }

    fun onIntent(intent: SplashIntent) {

    }

    private suspend fun login() {
        val isRefreshTokenFilled = checkRefreshTokenFilledUseCase()

        if (isRefreshTokenFilled) {
            _event.emit(SplashEvent.Login.Success)
        } else {
            _event.emit(SplashEvent.Login.Fail)
        }
    }
}
