package com.ray.template.android.presentation.ui.main.home

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.ui.main.home.mypage.MyPageConstant
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class HomeType(
    val route: String,
    @DrawableRes val iconRes: Int
) : Parcelable {

    @Parcelize
    data object MyPage : HomeType(
        route = MyPageConstant.ROUTE,
        iconRes = R.drawable.ic_account
    )

    companion object {
        fun values(): List<HomeType> {
            return listOf(MyPage)
        }
    }
}
