package com.ray.template.android.presentation.ui.main.home.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.presentation.common.theme.Body0
import com.ray.template.android.presentation.common.util.compose.ErrorObserver
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.presentation.common.util.coroutine.event.eventObserve
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val argument: MyPageArgument = Unit.let {
        val state by viewModel.state.collectAsStateWithLifecycle()

        MyPageArgument(
            state = state,
            event = viewModel.event,
            intent = viewModel::onIntent,
            handler = viewModel.handler
        )
    }

    ErrorObserver(viewModel)
    MyPageScreen(
        navController = navController,
        argument = argument
    )
}

@Composable
private fun MyPageScreen(
    navController: NavController,
    argument: MyPageArgument,
) {
    val (state, event, intent, handler) = argument
    val scope = rememberCoroutineScope() + handler

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "MyPageScreen",
            style = Body0
        )
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->

        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen(
        navController = rememberNavController(),
        argument = MyPageArgument(
            state = MyPageState.Init,
            event = MutableEventFlow(),
            intent = {},
            handler = CoroutineExceptionHandler { _, _ -> }
        )
    )
}
