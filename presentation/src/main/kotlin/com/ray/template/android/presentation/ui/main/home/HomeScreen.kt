package com.ray.template.android.presentation.ui.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.eventObserve
import com.ray.template.android.presentation.common.theme.Space24
import com.ray.template.android.presentation.common.theme.Space56
import com.ray.template.android.presentation.common.theme.White
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.ui.main.home.mypage.MyPageScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    argument: HomeArgument,
    data: HomeData
) {
    val (state, event, intent, logEvent, handler) = argument
    val scope = rememberCoroutineScope() + handler

    val homeTypeList = HomeType.values()

    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )
    var selectedHomeType: HomeType by remember { mutableStateOf(data.initialHomeType) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = pagerState,
        ) { page ->
            when (homeTypeList[page]) {
                HomeType.MyPage -> {
                    MyPageScreen(navController = navController)
                }
            }
        }

        HomeBottomBarScreen(
            itemList = homeTypeList,
            selectedHomeType = selectedHomeType,
            onClick = {
                selectedHomeType = it
            }
        )
    }

    LaunchedEffectWithLifecycle(event, handler) {
        event.eventObserve { event ->

        }
    }
}

@Composable
private fun HomeBottomBarScreen(
    itemList: List<HomeType>,
    selectedHomeType: HomeType,
    onClick: (HomeType) -> Unit
) {

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(Space56),
        containerColor = White
    ) {
        itemList.forEach { item ->
            NavigationBarItem(
                selected = item == selectedHomeType,
                icon = {
                    Icon(
                        modifier = Modifier.size(Space24),
                        painter = painterResource(id = item.iconRes),
                        contentDescription = "bottom icon"
                    )
                },
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        argument = HomeArgument(
            state = HomeState.Init,
            event = MutableEventFlow(),
            intent = {},
            logEvent = { _, _ -> },
            handler = CoroutineExceptionHandler { _, _ -> }
        ),
        data = HomeData(
            initialHomeType = HomeType.MyPage
        )
    )
}
