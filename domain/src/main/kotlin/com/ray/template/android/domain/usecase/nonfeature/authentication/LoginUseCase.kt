package com.ray.template.android.domain.usecase.nonfeature.authentication

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.ray.template.android.domain.repository.nonfeature.AuthenticationRepository
import com.ray.template.android.domain.usecase.nonfeature.user.GetProfileUseCase
import io.sentry.Sentry
import io.sentry.protocol.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val getProfileUseCase: GetProfileUseCase
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Result<Long> {
        return authenticationRepository.login(
            username = username,
            password = password
        ).onSuccess {
            getProfileUseCase().onSuccess { profile ->
                Sentry.setUser(
                    User().apply {
                        this.id = profile.id.toString()
                        this.name = profile.name
                        this.username = profile.nickname
                        this.email = profile.email
                    }
                )
                Firebase.analytics.run {
                    setUserId(profile.id.toString())
                    setUserProperty("name", profile.name)
                    setUserProperty("nickname", profile.nickname)
                    setUserProperty("email", profile.email)
                }
                Firebase.crashlytics.run {
                    setUserId(profile.id.toString())
                    setCustomKeys {
                        key("name", profile.name)
                        key("nickname", profile.nickname)
                        key("email", profile.email)
                    }
                }
            }.getOrThrow()
        }
    }
}
