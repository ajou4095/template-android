package com.ray.template.android.presentation.ui.main.debug

import androidx.lifecycle.SavedStateHandle
import com.ray.template.android.common.util.coroutine.event.EventFlow
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.asEventFlow
import com.ray.template.android.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DebugViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _state: MutableStateFlow<DebugState> = MutableStateFlow(DebugState.Init)
    val state: StateFlow<DebugState> = _state.asStateFlow()

    private val _event: MutableEventFlow<DebugEvent> = MutableEventFlow()
    val event: EventFlow<DebugEvent> = _event.asEventFlow()

    init {
        launch {

        }
    }

    fun onIntent(intent: DebugIntent) {

    }
}
