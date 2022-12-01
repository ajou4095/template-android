package com.ray.template.presentation.ui.common.modal.alert

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ray.template.core.common.util.event.Event
import com.ray.template.presentation.helper.common.modal.alert.AlertDialogFragmentHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlertDialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val bundle: Bundle? by lazy {
        AlertDialogFragmentHelper.getBundle(savedStateHandle)
    }

    val title: String by lazy {
        AlertDialogFragmentHelper.getTitle(bundle)
    }

    val message: String by lazy {
        AlertDialogFragmentHelper.getMessage(bundle)
    }

    val isTwoButton: Boolean by lazy {
        AlertDialogFragmentHelper.isTwoButton(bundle)
    }

    val cancelMessage: String by lazy {
        AlertDialogFragmentHelper.getCancelMessage(bundle)
    }

    val confirmMessage: String by lazy {
        AlertDialogFragmentHelper.getConfirmMessage(bundle)
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