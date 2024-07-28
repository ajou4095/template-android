package com.ray.template.android.presentation.ui.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ray.template.android.presentation.ui.main.home.homeDestination
import com.ray.template.android.presentation.ui.main.nonlogin.nonLoginNavGraphNavGraph
import com.ray.template.android.presentation.ui.main.splash.splashDestination

fun NavGraphBuilder.mainDestination(
    navController: NavController
) {
    splashDestination(navController = navController)
    nonLoginNavGraphNavGraph(navController = navController)
    homeDestination(navController = navController)
}
