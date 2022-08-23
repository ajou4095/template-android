package com.ray.template.core.common.util

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}