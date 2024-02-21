package com.ray.template.android.data.repository.nonfeature.user

import com.ray.template.android.data.remote.local.SharedPreferencesManager
import com.ray.template.android.domain.model.nonfeature.error.ServerException
import com.ray.template.android.domain.model.nonfeature.user.Profile
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import com.ray.template.android.domain.repository.nonfeature.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockUserRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val tokenRepository: TokenRepository
) : UserRepository {

    override suspend fun getProfile(): Result<Profile> {
        randomShortDelay()
        val isLogined = tokenRepository.accessToken.isNotEmpty()
        return if (isLogined) {
            Result.success(
                Profile(
                    id = 1,
                    name = "장성혁",
                    nickname = "Ray Jang",
                    email = "ajou4095@gmail.com"
                )
            )
        } else {
            Result.failure(
                ServerException("MOCK_ERROR", "로그인이 필요합니다.")
            )
        }
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 500).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }

    companion object {
        private const val IS_REGISTERED = "mock_is_registered"
    }
}
