package com.ray.template.android.common.util.coroutine.event

import kotlinx.coroutines.flow.Flow

interface EventFlow<T> : Flow<EventFlowSlot<in T>>

private class ReadOnlyEventFlow<T>(flow: EventFlow<T>) : EventFlow<T> by flow

fun <T> MutableEventFlow<T>.asEventFlow(): EventFlow<T> = ReadOnlyEventFlow(this)

suspend inline fun <reified S : T, T> EventFlow<T>.eventObserve(
    crossinline onEventUnhandledContent: (S) -> Unit
) {
    collect { event ->
        event.getContentIfNotHandled<S>()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
