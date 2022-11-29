package com.ray.template.core.common.util

import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * Safely run some delayed tasks in a view as they will be tied to the lifecycle.
 *
 * @param duration delay task in milliseconds
 * @param dispatcher CoroutineDispatcher, defaults to Dispatchers.Main as view work is done on Main thread
 * @param block the block of work to be done
 *
 * example:
 *
 * view.delayOnLifeCycle(500L) {
 *   // Do something
 *  }
 *
 * */
fun View.delayOnLifecycle(
    duration: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
    lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
        delay(duration)
        block()
    }
}