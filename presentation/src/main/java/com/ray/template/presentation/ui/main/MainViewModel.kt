package com.ray.template.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ray.template.domain.usecase.GetSampleInformationUseCase
import com.ray.template.presentation.model.SampleInformationModel
import com.ray.template.presentation.model.toUiModel
import com.ray.template.presentation.util.coroutine.event.EventFlow
import com.ray.template.presentation.util.coroutine.event.MutableEventFlow
import com.ray.template.presentation.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSampleInformationUseCase: GetSampleInformationUseCase
) : ViewModel() {

    private val _state: MutableEventFlow<MainState> = MutableEventFlow()
    val state: EventFlow<MainState> = _state.asEventFlow()

    private val _event: MutableEventFlow<MainViewEvent> = MutableEventFlow()
    val event: EventFlow<MainViewEvent> = _event.asEventFlow()

    private val _sampleInformation: MutableStateFlow<SampleInformationModel> = MutableStateFlow(SampleInformationModel())
    val sampleInformation: StateFlow<SampleInformationModel> = _sampleInformation.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(MainState.Init.Request)
        }
    }

    fun initialize() {
        viewModelScope.launch {
            _state.emit(MainState.Init.Loading)
            getSampleInformationUseCase()
                .onSuccess {
                    _sampleInformation.value = it.toUiModel()
                    _state.emit(MainState.Init.Success)
                }.onFailure {
                    _state.emit(MainState.Init.Fail(it))
                }
        }
    }

    fun doSomeAction() {
        viewModelScope.launch {
            _state.emit(MainState.SomeAction.Loading)
            delay(1000L)
            _state.emit(MainState.SomeAction.Success)
        }
    }

    fun onConfirm() {
        viewModelScope.launch {
            _event.emit(MainViewEvent.Confirm)
        }
    }
}
