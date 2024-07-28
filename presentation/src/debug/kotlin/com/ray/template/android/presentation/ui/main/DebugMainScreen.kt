package com.ray.template.android.presentation.ui.main

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Space40
import com.ray.template.android.presentation.common.theme.TemplateTheme
import com.ray.template.android.presentation.common.util.compose.ErrorObserver
import com.ray.template.android.presentation.common.util.compose.safeNavigate
import com.ray.template.android.presentation.common.view.RippleBox
import com.ray.template.android.presentation.ui.main.debug.DebugConstant
import com.ray.template.android.presentation.ui.main.debug.debugDestination
import com.ray.template.android.presentation.ui.main.splash.SplashConstant
import kotlin.math.roundToInt

@Composable
fun DebugMainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    TemplateTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = SplashConstant.ROUTE
        ) {
            mainDestination(navController)
            debugDestination(navController)
        }

        ErrorObserver(viewModel)
        MainScreenRefreshFailDialog(navController, viewModel.refreshFailEvent)
        DebugPopup(navController)
    }
}

@Composable
private fun DebugPopup(
    navController: NavController
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    RippleBox(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            },
        onClick = {
            navController.safeNavigate(DebugConstant.ROUTE)
        }
    ) {
        Icon(
            modifier = Modifier.size(Space40),
            painter = painterResource(R.drawable.ic_more_vertical),
            contentDescription = null,
            tint = Blue
        )
    }
}
