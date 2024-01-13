package com.ray.template.common

fun Char?.orEmpty(): Char {
    return this ?: Char.MIN_VALUE
}