package com.ray.template.presentation.common.util

import android.graphics.Paint
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter


@BindingAdapter("underline")
fun AppCompatTextView.setUnderline(
    isUnderline: Boolean
) {
    paintFlags = if (isUnderline) {
        paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
    }
}

fun AppCompatEditText.showKeyboard() {
    requestFocus()
    context.getSystemService(InputMethodManager::class.java)
        ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun AppCompatEditText.hideKeyboard() {
    context.getSystemService(InputMethodManager::class.java)
        ?.hideSoftInputFromWindow(windowToken, 0)
}
