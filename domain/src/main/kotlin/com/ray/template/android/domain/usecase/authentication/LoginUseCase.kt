package com.ray.template.android.domain.usecase.authentication

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.ray.template.android.domain.repository.AuthenticationRepository
import com.ray.template.android.domain.usecase.user.GetProfileUseCase
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
                Firebase.analytics.run {
                    setUserId(profile.id.toString())
                    setUserProperty("email", profile.email)
                    setUserProperty("name", profile.name)
                    setUserProperty("nickname", profile.nickname)
                }
                Sentry.setUser(
                    User().apply {
                        this.id = profile.id.toString()
                        this.email = profile.email
                        this.name = profile.name
                        this.username = profile.nickname
                    }
                )
            }.getOrThrow()
        }
    }
}
