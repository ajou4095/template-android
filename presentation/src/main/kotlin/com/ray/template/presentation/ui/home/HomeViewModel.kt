package com.ray.template.presentation.ui.home

import com.ray.template.presentation.common.base.BaseViewModel
import com.ray.template.presentation.common.util.coroutine.event.EventFlow
import com.ray.template.presentation.common.util.coroutine.event.MutableEventFlow
import com.ray.template.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Init)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _event: MutableEventFlow<HomeViewEvent> = MutableEventFlow()
    val event: EventFlow<HomeViewEvent> = _event.asEventFlow()

    fun onConfirm() {
        launch {
            _event.emit(HomeViewEvent.Confirm)
        }
    }
}
