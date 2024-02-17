package com.ray.template.presentation.ui.main.splash

import androidx.lifecycle.SavedStateHandle
import com.ray.template.presentation.common.base.BaseViewModel
import com.ray.template.presentation.common.util.coroutine.event.EventFlow
import com.ray.template.presentation.common.util.coroutine.event.MutableEventFlow
import com.ray.template.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
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
        _state.value = SplashState.Loading
        delay(1000L)
        _event.emit(SplashEvent.Login.Success)
        _state.value = SplashState.Init
    }
}
