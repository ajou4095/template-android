package com.ray.template.android.presentation.ui.main.nonlogin.onboarding

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
class OnBoardingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _state: MutableStateFlow<OnBoardingState> = MutableStateFlow(OnBoardingState.Init)
    val state: StateFlow<OnBoardingState> = _state.asStateFlow()

    private val _event: MutableEventFlow<OnBoardingEvent> = MutableEventFlow()
    val event: EventFlow<OnBoardingEvent> = _event.asEventFlow()

    fun onIntent(intent: OnBoardingIntent) {

    }
}
