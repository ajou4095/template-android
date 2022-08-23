package com.ray.template.core.common.util

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Float?.orZero(): Float {
    return this ?: 0f
}

fun Long?.orZero(): Long {
    return this ?: 0L
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Short?.orZero(): Short {
    return this ?: 0
}

fun Byte?.orZero(): Byte {
    return this ?: 0
}
