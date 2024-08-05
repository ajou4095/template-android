package com.ray.template.android.presentation.ui.main.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.eventObserve
import com.ray.template.android.presentation.common.theme.Gray900
import com.ray.template.android.presentation.common.theme.Headline0
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@Composable
fun DebugScreen(
    navController: NavController,
    argument: DebugArgument,
) {
    val (state, event, intent, logEvent, coroutineContext) = argument
    val scope = rememberCoroutineScope() + coroutineContext
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Debug Page",
            style = Headline0.merge(Gray900)
        )
    }

    LaunchedEffectWithLifecycle(event, coroutineContext) {
        event.eventObserve { event ->

        }
    }
}

@Preview
@Composable
private fun DebugScreenPreview() {
    DebugScreen(
        navController = rememberNavController(),
        argument = DebugArgument(
            state = DebugState.Init,
            event = MutableEventFlow(),
            intent = {},
            logEvent = { _, _ -> },
            coroutineContext = CoroutineExceptionHandler { _, _ -> }
        )
    )
}
