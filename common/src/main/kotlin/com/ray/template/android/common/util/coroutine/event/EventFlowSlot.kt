package com.ray.template.android.common.util.coroutine.event

import java.util.concurrent.atomic.AtomicBoolean

class EventFlowSlot<T>(val value: T) {

    val hasBeenHandled: AtomicBoolean = AtomicBoolean(false)

    inline fun <reified S : T> getContentIfNotHandled(): S? {
        return if (
            value is S &&
            hasBeenHandled.compareAndSet(false, true)
        ) {
            value
        } else {
            null
        }
    }
}
