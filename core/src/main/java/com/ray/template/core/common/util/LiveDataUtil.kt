package com.ray.template.core.common.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.ray.template.core.common.util.event.Event
import com.ray.template.core.common.util.event.EventObserver

val LiveData<Boolean>.valueOrDefault: Boolean
    get() = value ?: false

val LiveData<Byte>.valueOrDefault: Byte
    get() = value ?: 0

val LiveData<Char>.valueOrDefault: Char
    get() = value ?: Char.MIN_VALUE

val LiveData<Double>.valueOrDefault: Double
    get() = value ?: 0.0

val LiveData<Float>.valueOrDefault: Float
    get() = value ?: 0f

val LiveData<Int>.valueOrDefault: Int
    get() = value ?: 0

val LiveData<Long>.valueOrDefault: Long
    get() = value ?: 0L

val LiveData<Short>.valueOrDefault: Short
    get() = value ?: 0

val LiveData<String>.valueOrDefault: String
    get() = value ?: ""

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, onEventUnhandledContent: (T) -> Unit) {
    this.observe(owner, EventObserver(onEventUnhandledContent))
}