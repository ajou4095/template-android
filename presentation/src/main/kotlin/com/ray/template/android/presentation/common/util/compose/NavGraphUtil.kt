package com.ray.template.android.presentation.common.util.compose

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

fun makeRoute(
    route: String,
    pairs: List<Pair<String, Any>>
): String {
    return makeRoute(route, pairs.toMap())
}

fun makeRoute(
    route: String,
    arguments: Map<String, Any> = emptyMap(),
): String {
    return route + arguments.entries.joinToString(
        separator = "&",
        prefix = "?"
    ) { "${it.key}=${it.value}" }
}

fun parseRoute(
    route: String
): Pair<String, Map<String, Any>> {
    val routeSplit = route.split("?")
    val routePath = routeSplit.getOrNull(0).orEmpty()
    val routeArguments = routeSplit.getOrNull(1)?.split("&")?.associate {
        val argumentSplit = it.split("=")
        argumentSplit.getOrNull(0).orEmpty() to argumentSplit.getOrNull(1).orEmpty()
    }.orEmpty()

    return routePath to routeArguments
}

@MainThread
fun NavController.safeNavigate(
    route: String,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) : Boolean {
    if (currentBackStackEntry?.lifecycleIsResumed() == true) {
        builder?.let {
            navigate(route, navOptions(it))
        } ?: navigate(route)
        return true
    }
    return false
}

@MainThread
fun NavController.safeNavigateUp() : Boolean {
    if (currentBackStackEntry?.lifecycleIsResumed() == true) {
        navigateUp()
        return true
    }
    return false
}

fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
    return this.lifecycle.currentState == Lifecycle.State.RESUMED
}
