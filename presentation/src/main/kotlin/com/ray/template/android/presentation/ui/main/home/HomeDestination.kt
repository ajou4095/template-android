package com.ray.template.android.presentation.ui.main.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ray.template.android.presentation.common.util.compose.ErrorObserver

fun NavGraphBuilder.homeDestination(
    navController: NavController
) {
    composable(
        route = HomeConstant.ROUTE
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        val argument: HomeArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val initialHomeType = HomeType.MyPage

            HomeArgument(
                state = state,
                initialHomeType = initialHomeType,
                event = viewModel.event,
                intent = viewModel::onIntent,
                handler = viewModel.handler
            )
        }

        ErrorObserver(viewModel)
        HomeScreen(
            navController = navController,
            argument = argument
        )
    }
}
