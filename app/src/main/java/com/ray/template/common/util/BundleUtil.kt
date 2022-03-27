package com.ray.template.common.util

import androidx.lifecycle.SavedStateHandle

fun SavedStateHandle.getByteOrDefault(
    key: String,
    defaultValue: Byte = 0
): Byte {
    return get<Byte>(key) ?: defaultValue
}

fun SavedStateHandle.getIntOrDefault(
    key: String,
    defaultValue: Int = 0
): Int {
    return get<Int>(key) ?: defaultValue
}

fun SavedStateHandle.getFloatOrDefault(
    key: String,
    defaultValue: Float = 0f
): Float {
    return get<Float>(key) ?: defaultValue
}

fun SavedStateHandle.getStringOrDefault(
    key: String,
    defaultValue: String = ""
): String {
    return get<String>(key) ?: defaultValue
}