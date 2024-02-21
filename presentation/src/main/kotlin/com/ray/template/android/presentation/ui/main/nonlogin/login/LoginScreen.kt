package com.ray.template.android.presentation.ui.main.nonlogin.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.eventObserve
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Headline0
import com.ray.template.android.presentation.common.theme.Space12
import com.ray.template.android.presentation.common.theme.Space20
import com.ray.template.android.presentation.common.theme.White
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.common.view.confirm.ConfirmButton
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonProperties
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonSize
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonType
import com.ray.template.android.presentation.ui.main.home.HomeConstant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    argument: LoginArgument
) {
    val (state, event, intent, logEvent, handler) = argument
    val scope = rememberCoroutineScope() + handler
    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )

    fun navigateToHome() {
        navController.navigate(HomeConstant.ROUTE) {
            popUpTo(LoginConstant.ROUTE) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Spacer(modifier = Modifier.height(Space20))
        Text(
            modifier = Modifier.padding(horizontal = Space20),
            text = stringResource(R.string.app_name),
            style = Headline0
        )
        Spacer(modifier = Modifier.weight(1f))
        ConfirmButton(
            modifier = Modifier
                .padding(horizontal = Space20, vertical = Space12)
                .fillMaxWidth(),
            properties = ConfirmButtonProperties(
                size = ConfirmButtonSize.Large,
                type = ConfirmButtonType.Primary
            ),
            onClick = {
                intent(LoginIntent.OnConfirm)
            }
        ) { style ->
            Text(
                text = "로그인",
                style = style
            )
        }
    }

    fun login(event: LoginEvent.Login) {
        when (event) {
            LoginEvent.Login.Success -> {
                navigateToHome()
            }
        }
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->
            when (event) {
                is LoginEvent.Login -> login(event)
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        argument = LoginArgument(
            state = LoginState.Init,
            event = MutableEventFlow(),
            intent = {},
            logEvent = { _, _ -> },
            handler = CoroutineExceptionHandler { _, _ -> }
        )
    )
}
