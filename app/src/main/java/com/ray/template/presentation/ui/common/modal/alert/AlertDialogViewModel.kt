package com.ray.template.presentation.ui.common.modal.alert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ray.template.common.util.livedata.Event
import com.ray.template.presentation.helper.common.modal.alert.AlertDialogFragmentHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlertDialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val title: String by lazy {
        AlertDialogFragmentHelper.getTitle(savedStateHandle)
    }

    val message: String by lazy {
        AlertDialogFragmentHelper.getMessage(savedStateHandle)
    }

    val isTwoButton: Boolean by lazy {
        AlertDialogFragmentHelper.isTwoButton(savedStateHandle)
    }

    val cancelMessage: String by lazy {
        AlertDialogFragmentHelper.getCancelMessage(savedStateHandle)
    }

    val confirmMessage: String by lazy {
        AlertDialogFragmentHelper.getConfirmMessage(savedStateHandle)
    }

    private val _event = MutableLiveData<Event<AlertDialogViewEvent>>()
    val event: LiveData<Event<AlertDialogViewEvent>>
        get() = _event

    fun onCancel() {
        _event.value = Event(AlertDialogViewEvent.OnCancel)
    }

    fun onConfirm() {
        _event.value = Event(AlertDialogViewEvent.OnConfirm)
    }
}