package com.ray.template.android.presentation.ui.main.nonlogin.login

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ray.template.android.presentation.common.util.compose.ErrorObserver

fun NavGraphBuilder.loginDestination(
    navController: NavController
) {
    composable(
        route = LoginConstant.ROUTE
    ) {
        val viewModel: LoginViewModel = hiltViewModel()

        val argument: LoginArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            LoginArgument(
                state = state,
                event = viewModel.event,
                intent = viewModel::onIntent,
                logEvent = viewModel::logEvent,
                handler = viewModel.handler
            )
        }

        ErrorObserver(viewModel)
        LoginScreen(
            navController = navController,
            argument = argument
        )
    }
}
