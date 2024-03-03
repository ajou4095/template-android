package com.ray.template.android.presentation.ui.main.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeData(
    val initialHomeType: HomeType,
    val homeTypeList: List<HomeType>
)
