package com.ray.template.android.presentation.ui.main.nonlogin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ray.template.android.presentation.ui.main.nonlogin.login.loginDestination
import com.ray.template.android.presentation.ui.main.nonlogin.onboarding.OnBoardingConstant
import com.ray.template.android.presentation.ui.main.nonlogin.onboarding.onBoardingDestination

fun NavGraphBuilder.nonLoginNavGraphNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = OnBoardingConstant.ROUTE,
        route = NonLoginConstant.ROUTE
    ) {
        onBoardingDestination(navController = navController)
        loginDestination(navController = navController)
    }
}
