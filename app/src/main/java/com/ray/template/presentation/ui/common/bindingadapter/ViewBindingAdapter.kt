package com.ray.template.presentation.ui.common.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(isVisible: Boolean?) {
    this.isVisible = (isVisible == true)
}

@BindingAdapter("enabled")
fun View.setViewEnabled(isEnabled: Boolean?) {
    this.isEnabled = isEnabled ?: false
    if (this.isEnabled) requestFocus()
}

@BindingAdapter("activated")
fun View.setViewActivated(isActivated: Boolean?) {
    this.isActivated = isActivated ?: false
}
