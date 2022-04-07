package com.ray.template.presentation.ui.common.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(isVisible: Boolean?) {
    this.isVisible = (isVisible == true)
}