package com.ray.template.android.common

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}
