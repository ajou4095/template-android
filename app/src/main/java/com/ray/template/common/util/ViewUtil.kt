package com.ray.template.common.util

import android.content.res.Resources
import android.util.TypedValue

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