package com.ray.template.presentation.helper.common.modal.alert

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import com.ray.template.common.util.getBooleanOrDefault
import com.ray.template.common.util.getStringOrDefault
import com.ray.template.presentation.ui.common.modal.alert.AlertDialogFragment
import com.ray.template.presentation.ui.common.modal.alert.AlertDialogFragmentContract

object AlertDialogFragmentHelper {
    private const val TITLE = "key_TITLE"
    private const val MESSAGE = "key_MESSAGE"
    private const val IS_TWO_BUTTON = "key_IS_TWO_BUTTON"
    private const val CANCEL_MESSAGE = "key_CANCEL_MESSAGE"
    private const val CONFIRM_MESSAGE = "key_CONFIRM_MESSAGE"

    fun newInstance(
        title: String? = null,
        message: String? = null,
        isTwoButton: Boolean = false,
        cancelMessage: String? = AlertDialogFragmentContract.STRING_CANCEL,
        confirmMessage: String? = AlertDialogFragmentContract.STRING_CONFIRM,
        onCancel: (() -> Unit)? = null,
        onConfirm: (() -> Any)? = null
    ): AlertDialogFragment {
        val args = Bundle().apply {
            putString(TITLE, title)
            putString(MESSAGE, message)
            putBoolean(IS_TWO_BUTTON, isTwoButton)
            putString(CANCEL_MESSAGE, cancelMessage)
            putString(CONFIRM_MESSAGE, confirmMessage)
        }
        val fragment = AlertDialogFragment().apply {
            arguments = args
            this.onCancel = onCancel
            this.onConfirm = onConfirm
        }
        return fragment
    }

    fun getTitle(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle.getStringOrDefault(TITLE)
    }

    fun getMessage(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle.getStringOrDefault(MESSAGE)
    }

    fun isTwoButton(savedStateHandle: SavedStateHandle): Boolean {
        return savedStateHandle.getBooleanOrDefault(IS_TWO_BUTTON)
    }

    fun getCancelMessage(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle.getStringOrDefault(CANCEL_MESSAGE)
    }

    fun getConfirmMessage(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle.getStringOrDefault(CONFIRM_MESSAGE)
    }
}