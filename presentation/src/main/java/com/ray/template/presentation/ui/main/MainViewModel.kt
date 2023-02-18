package com.ray.template.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ray.template.common.event.Event
import com.ray.template.domain.usecase.GetSampleInformationUseCase
import com.ray.template.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSampleInformationUseCase: GetSampleInformationUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Event<MainState>>()
    val state: LiveData<Event<MainState>>
        get() = _state

    private val _event = MutableLiveData<Event<MainViewEvent>>()
    val event: LiveData<Event<MainViewEvent>>
        get() = _event

    init {
        _state.value = Event(MainState.Init.Request)
    }

    fun initialize() {
        viewModelScope.launch(Dispatchers.Main) {
            getSampleInformationUseCase()
                .onStart {
                    _state.value = Event(MainState.Init.Loading)
                }.catch {
                    _state.value = Event(MainState.Init.Fail(it))
                }.collect {
                    Timber.d("UseCase Result : ${it.toUiModel()}")
                    _state.value = Event(MainState.Init.Success)
                }
        }
    }

    fun onConfirm() {
        _event.value = Event(MainViewEvent.Confirm)
    }
}
