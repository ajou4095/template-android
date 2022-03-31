package com.ray.template.common.util

import androidx.lifecycle.SavedStateHandle

fun SavedStateHandle.getBooleanOrDefault(
    key: String,
    defaultValue: Boolean = false
): Boolean {
    return get<Boolean>(key) ?: defaultValue
}

fun SavedStateHandle.getByteOrDefault(
    key: String,
    defaultValue: Byte = 0
): Byte {
    return get<Byte>(key) ?: defaultValue
}

fun SavedStateHandle.getCharOrDefault(
    key: String,
    defaultValue: Char = Char.MIN_VALUE
): Char {
    return get<Char>(key) ?: defaultValue
}

fun SavedStateHandle.getDoubleOrDefault(
    key: String,
    defaultValue: Double = 0.0
): Double {
    return get<Double>(key) ?: defaultValue
}

fun SavedStateHandle.getFloatOrDefault(
    key: String,
    defaultValue: Float = 0f
): Float {
    return get<Float>(key) ?: defaultValue
}

fun SavedStateHandle.getIntOrDefault(
    key: String,
    defaultValue: Int = 0
): Int {
    return get<Int>(key) ?: defaultValue
}

fun SavedStateHandle.getLongOrDefault(
    key: String,
    defaultValue: Long = 0L
): Long {
    return get<Long>(key) ?: defaultValue
}

fun SavedStateHandle.getShortOrDefault(
    key: String,
    defaultValue: Short = 0
): Short {
    return get<Short>(key) ?: defaultValue
}

fun SavedStateHandle.getStringOrDefault(
    key: String,
    defaultValue: String = ""
): String {
    return get<String>(key) ?: defaultValue
}