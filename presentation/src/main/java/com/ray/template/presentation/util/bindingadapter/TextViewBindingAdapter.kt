package com.ray.template.presentation.util.bindingadapter

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun TextView.visibleOrGone(text: CharSequence?) {
    this.isVisible = !text.isNullOrEmpty()
}