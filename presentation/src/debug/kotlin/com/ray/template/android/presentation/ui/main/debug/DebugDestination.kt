package com.ray.template.android.presentation.ui.main.debug

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ray.template.android.presentation.common.util.compose.ErrorObserver

fun NavGraphBuilder.debugDestination(
    navController: NavController
) {
    composable(
        route = DebugConstant.ROUTE
    ) {
        val viewModel: DebugViewModel = hiltViewModel()

        val argument: DebugArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            DebugArgument(
                state = state,
                event = viewModel.event,
                intent = viewModel::onIntent,
                logEvent = viewModel::logEvent,
                handler = viewModel.handler
            )
        }

        ErrorObserver(viewModel)
        DebugScreen(
            navController = navController,
            argument = argument
        )
    }
}
