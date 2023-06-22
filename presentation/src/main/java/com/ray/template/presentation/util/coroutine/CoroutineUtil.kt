package com.ray.template.presentation.util.coroutine

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private fun repeatOnStarted(
    lifecycleOwner: LifecycleOwner,
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

fun AppCompatActivity.repeatOnStarted(
    block: suspend CoroutineScope.() -> Unit
) {
    repeatOnStarted(this, block)
}

fun Fragment.repeatOnStarted(
    block: suspend CoroutineScope.() -> Unit
) {
    repeatOnStarted(viewLifecycleOwner, block)
}
