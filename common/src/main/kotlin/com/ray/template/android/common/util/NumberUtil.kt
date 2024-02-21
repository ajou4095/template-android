package com.ray.template.android.common.util

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Double.ifZero(
    block: () -> Double
): Double {
    return if (this == 0.0) {
        block()
    } else {
        this
    }
}

fun Float?.orZero(): Float {
    return this ?: 0f
}

fun Float.ifZero(
    block: () -> Float
): Float {
    return if (this == 0f) {
        block()
    } else {
        this
    }
}

fun Long?.orZero(): Long {
    return this ?: 0L
}

fun Long.ifZero(
    block: () -> Long
): Long {
    return if (this == 0L) {
        block()
    } else {
        this
    }
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Int.ifZero(
    block: () -> Int
): Int {
    return if (this == 0) {
        block()
    } else {
        this
    }
}

fun Short?.orZero(): Short {
    return this ?: 0
}

fun Short.ifZero(
    block: () -> Short
): Short {
    return if (this == 0.toShort()) {
        block()
    } else {
        this
    }
}

fun Byte?.orZero(): Byte {
    return this ?: 0
}

fun Byte.ifZero(
    block: () -> Byte
): Byte {
    return if (this == 0.toByte()) {
        block()
    } else {
        this
    }
}
