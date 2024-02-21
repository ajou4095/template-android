package com.ray.template.android.presentation.ui.main.splash

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ray.template.android.presentation.common.util.compose.ErrorObserver

fun NavGraphBuilder.splashDestination(
    navController: NavController
) {
    composable(
        route = SplashConstant.ROUTE
    ) {
        val viewModel: SplashViewModel = hiltViewModel()

        val argument: SplashArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            SplashArgument(
                state = state,
                event = viewModel.event,
                intent = viewModel::onIntent,
                logEvent = viewModel::logEvent,
                handler = viewModel.handler
            )
        }

        ErrorObserver(viewModel)
        SplashScreen(
            navController = navController,
            argument = argument
        )
    }
}
