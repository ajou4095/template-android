package com.ray.template.common.util

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}