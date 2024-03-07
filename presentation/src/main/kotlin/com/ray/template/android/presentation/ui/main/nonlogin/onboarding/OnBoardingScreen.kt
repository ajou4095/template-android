package com.ray.template.android.presentation.ui.main.nonlogin.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.eventObserve
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Body0
import com.ray.template.android.presentation.common.theme.Gray400
import com.ray.template.android.presentation.common.theme.Gray900
import com.ray.template.android.presentation.common.theme.Space12
import com.ray.template.android.presentation.common.theme.Space16
import com.ray.template.android.presentation.common.theme.Space20
import com.ray.template.android.presentation.common.theme.Space32
import com.ray.template.android.presentation.common.theme.Space4
import com.ray.template.android.presentation.common.theme.Space8
import com.ray.template.android.presentation.common.theme.White
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.common.util.compose.safeNavigate
import com.ray.template.android.presentation.common.view.confirm.ConfirmButton
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonProperties
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonSize
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonType
import com.ray.template.android.presentation.ui.main.nonlogin.login.LoginConstant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    argument: OnBoardingArgument
) {
    val (state, event, intent, logEvent, handler) = argument
    val scope = rememberCoroutineScope() + handler
    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )

    fun navigateToLogin() {
        navController.safeNavigate(LoginConstant.ROUTE) {
            popUpTo(OnBoardingConstant.ROUTE) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = Space16)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
            ) { page ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Space32)
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = page.toString(),
                        style = Body0
                    )
                }
            }

            Spacer(Modifier.height(Space32))

            Row(
                Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                repeat(pagerState.pageCount) { index ->
                    val isSelected = pagerState.currentPage == index

                    val color by animateColorAsState(
                        targetValue = if (isSelected) Gray900 else Gray400,
                        label = "iteration color"
                    )

                    Box(
                        modifier = Modifier
                            .padding(Space4)
                            .clip(CircleShape)
                            .background(color)
                            .size(Space8)
                    )
                }
            }
        }

        ConfirmButton(
            properties = ConfirmButtonProperties(
                size = ConfirmButtonSize.Large,
                type = ConfirmButtonType.Primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = Space20, vertical = Space12),
            onClick = {
                navigateToLogin()
            }
        ) { style ->
            Text(
                text = stringResource(R.string.onboarding_confirm),
                style = style
            )
        }
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->

        }
    }
}

@Preview
@Composable
private fun OnBoardingScreenPreview() {
    OnBoardingScreen(
        navController = rememberNavController(),
        argument = OnBoardingArgument(
            state = OnBoardingState.Init,
            event = MutableEventFlow(),
            intent = {},
            logEvent = { _, _ -> },
            handler = CoroutineExceptionHandler { _, _ -> }
        )
    )
}
