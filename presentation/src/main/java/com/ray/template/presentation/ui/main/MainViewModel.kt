package com.ray.template.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ray.template.domain.usecase.GetSampleInformationUseCase
import com.ray.template.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    getSampleInformationUseCase: GetSampleInformationUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            getSampleInformationUseCase().collect {
                Timber.d("UseCase Result : ${it.toUiModel()}")
            }
        }
    }
}