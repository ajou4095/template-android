package com.ray.template.core.util

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}