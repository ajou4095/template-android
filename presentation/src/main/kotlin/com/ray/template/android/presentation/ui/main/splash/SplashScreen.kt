package com.ray.template.android.presentation.ui.main.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Headline1
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.presentation.common.util.coroutine.event.eventObserve
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@Composable
fun SplashScreen(
    navController: NavController,
    argument: SplashArgument,
) {
    val (state, event, intent, handler) = argument
    val scope = rememberCoroutineScope() + handler

    fun navigateToHome() {
//        navController.navigate(HomeConstant.ROUTE) {
//            popUpTo(HomeConstant.ROUTE)
//        }
    }

    fun login(event: SplashEvent.Login) {
        when (event) {
            is SplashEvent.Login.Success -> {
                navigateToHome()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = Headline1
        )
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->
            when (event) {
                is SplashEvent.Login -> login(event)
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        navController = rememberNavController(),
        argument = SplashArgument(
            state = SplashState.Init,
            event = MutableEventFlow(),
            intent = {},
            handler = CoroutineExceptionHandler { _, _ -> }
        )
    )
}
