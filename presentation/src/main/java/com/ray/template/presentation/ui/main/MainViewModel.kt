package com.ray.template.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ray.template.domain.usecase.GetSampleInformationUseCase
import com.ray.template.presentation.model.SampleInformationModel
import com.ray.template.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSampleInformationUseCase: GetSampleInformationUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<MainState>()
    val state: SharedFlow<MainState>
        get() = _state

    private val _event = MutableSharedFlow<MainViewEvent>()
    val event: SharedFlow<MainViewEvent>
        get() = _event

    private val _sampleInformation = MutableStateFlow(SampleInformationModel())
    val sampleInformation: StateFlow<SampleInformationModel>
        get() = _sampleInformation

    init {
        viewModelScope.launch {
            _state.emit(MainState.Init.Request)
        }
    }

    fun initialize() {
        viewModelScope.launch(Dispatchers.Main) {
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
        viewModelScope.launch(Dispatchers.Main) {
            _state.emit(MainState.SomeAction.Loading)
            withContext(Dispatchers.IO) {
                delay(1000L)
            }
            _state.emit(MainState.SomeAction.Success)
        }
    }

    fun onConfirm() {
        viewModelScope.launch {
            _event.emit(MainViewEvent.Confirm)
        }
    }
}
