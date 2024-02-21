package com.ray.template.android.presentation.ui.main.home.mypage

import androidx.compose.runtime.Immutable
import com.ray.template.android.domain.model.nonfeature.user.Profile

@Immutable
data class MyPageData(
    val profile: Profile
)
