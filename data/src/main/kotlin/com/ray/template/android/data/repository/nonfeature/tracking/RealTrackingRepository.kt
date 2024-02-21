package com.ray.template.android.data.repository.nonfeature.tracking

import androidx.annotation.Size
import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.ray.template.android.domain.model.nonfeature.user.Profile
import com.ray.template.android.domain.repository.nonfeature.TrackingRepository
import io.sentry.Sentry
import io.sentry.protocol.User
import javax.inject.Inject

class RealTrackingRepository @Inject constructor() : TrackingRepository {

    override suspend fun setProfile(
        profile: Profile
    ): Result<Unit> {
        return runCatching {
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
        }
    }

    override suspend fun logEvent(
        @Size(min = 1, max = 40) eventName: String,
        params: Map<String, Any>
    ): Result<Unit> {
        return runCatching {
            Firebase.analytics.logEvent(
                eventName,
                bundleOf(
                    *params.map { (key, value) ->
                        when (value) {
                            is String -> key to value
                            is Int -> key to value.toLong()
                            is Long -> key to value
                            is Float -> key to value.toDouble()
                            is Double -> key to value
                            is Boolean -> key to value.toString()
                            else -> throw IllegalArgumentException("Invalid value type")
                        }
                    }.toTypedArray()
                )
            )
        }
    }
}
