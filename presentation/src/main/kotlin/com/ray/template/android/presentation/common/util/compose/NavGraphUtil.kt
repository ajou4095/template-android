package com.ray.template.android.presentation.common.util.compose

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
