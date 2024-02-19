package com.ray.template.android.presentation.common.view.confirm

sealed interface ConfirmButtonType {
    data object Primary : ConfirmButtonType
    data object Secondary : ConfirmButtonType
    data object Tertiary : ConfirmButtonType
    data object Outline : ConfirmButtonType
}
