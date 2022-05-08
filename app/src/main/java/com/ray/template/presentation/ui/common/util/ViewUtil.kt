package com.ray.template.presentation.ui.common.util

import android.content.res.Resources
import android.content.res.TypedArray
import android.util.TypedValue
import androidx.annotation.StyleableRes
import androidx.core.content.res.getBooleanOrThrow
import androidx.core.content.res.getFloatOrThrow
import androidx.core.content.res.getIntegerOrThrow
import androidx.core.content.res.getStringOrThrow
import androidx.core.content.res.getTextOrThrow

val Int.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }

fun getDisplayWidth() = Resources.getSystem().displayMetrics.widthPixels

fun getDisplayHeight() = Resources.getSystem().displayMetrics.heightPixels

inline fun TypedArray.getTextIfCan(
    @StyleableRes index: Int,
    onSuccess: (CharSequence) -> Unit
) {
    if (hasValue(index)) {
        val value = getTextOrThrow(index)
        onSuccess(value)
    }
}

inline fun TypedArray.getStringIfCan(
    @StyleableRes index: Int,
    onSuccess: (String) -> Unit
) {
    if (hasValue(index)) {
        val value = getStringOrThrow(index)
        onSuccess(value)
    }
}

fun TypedArray.getIntegerOrNull(@StyleableRes index: Int): Int? {
    return if (hasValue(index)) {
        getIntegerOrThrow(index)
    } else {
        null
    }
}

inline fun TypedArray.getIntegerIfCan(
    @StyleableRes index: Int,
    onSuccess: (Int) -> Unit
) {
    if (hasValue(index)) {
        val value = getIntegerOrThrow(index)
        onSuccess(value)
    }
}

fun TypedArray.getBooleanOrNull(@StyleableRes index: Int): Boolean? {
    return if (hasValue(index)) {
        getBooleanOrThrow(index)
    } else {
        null
    }
}

inline fun TypedArray.getBooleanIfCan(
    @StyleableRes index: Int,
    onSuccess: (Boolean) -> Unit
) {
    if (hasValue(index)) {
        val value = getBooleanOrThrow(index)
        onSuccess(value)
    }
}

fun TypedArray.getFloatOrNull(@StyleableRes index: Int): Float? {
    return if (hasValue(index)) {
        getFloatOrThrow(index)
    } else {
        null
    }
}

inline fun TypedArray.getFloatIfCan(
    @StyleableRes index: Int,
    onSuccess: (Float) -> Unit
) {
    if (hasValue(index)) {
        val value = getFloatOrThrow(index)
        onSuccess(value)
    }
}

fun TypedArray.getLong(
    @StyleableRes index: Int,
    defValue: Long
): Long {
    return getLongOrNull(index) ?: defValue
}

fun TypedArray.getLongOrNull(@StyleableRes index: Int): Long? {
    return getIntegerOrNull(index)?.toLong()
}

inline fun TypedArray.getLongIfCan(
    @StyleableRes index: Int,
    onSuccess: (Long) -> Unit
) {
    getIntegerIfCan(index) {
        onSuccess(it.toLong())
    }
}