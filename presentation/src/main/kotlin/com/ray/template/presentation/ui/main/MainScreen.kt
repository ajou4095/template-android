package com.ray.template.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ray.template.presentation.common.theme.TemplateTheme
import com.ray.template.presentation.ui.main.nonlogin.nonLoginNavGraphNavGraph
import com.ray.template.presentation.ui.main.splash.SplashConstant
import com.ray.template.presentation.ui.main.splash.splashDestination

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    TemplateTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = SplashConstant.ROUTE
        ) {
            splashDestination(navController = navController)
            nonLoginNavGraphNavGraph(navController = navController)
        }
    }
}
