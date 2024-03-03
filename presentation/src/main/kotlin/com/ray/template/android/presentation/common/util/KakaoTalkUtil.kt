package com.ray.template.android.presentation.common.util

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

fun loginWithKakao(
    context: Context,
    onSuccess: (OAuthToken) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    loginWithKakaoTalk(
        context = context,
        onSuccess = onSuccess,
        onFailure = { exception ->
            if ((exception as? ClientError)?.reason == ClientErrorCause.NotSupported) {
                loginWithKakaoAccount(
                    context = context,
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            } else {
                onFailure(exception)
            }
        }
    )
}

fun loginWithKakaoTalk(
    context: Context,
    onSuccess: (OAuthToken) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        token?.let { onSuccess(it) }
        error?.let { onFailure(it) }
    }
}

fun loginWithKakaoAccount(
    context: Context,
    onSuccess: (OAuthToken) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        token?.let { onSuccess(it) }
        error?.let { onFailure(it) }
    }
}
