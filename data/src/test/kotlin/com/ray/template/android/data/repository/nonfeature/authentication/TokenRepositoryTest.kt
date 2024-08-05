package com.ray.template.android.data.repository.nonfeature.authentication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ray.template.android.domain.repository.nonfeature.TokenRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(
    application = HiltTestApplication::class,
    manifest = Config.NONE
)
class TokenRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var scope: TestScope

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    @Inject
    lateinit var tokenRepository: TokenRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test() {
        assertTrue(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun removeToken() = scope.runTest {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("refresh_token")] = ""
            preferences[stringPreferencesKey("access_token")] = ""
        }
        advanceUntilIdle()

        tokenRepository.removeToken()
        advanceUntilIdle()

        val (refreshToken, accessToken) = dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("refresh_token")] to
                    preferences[stringPreferencesKey("access_token")]
        }.first()
        advanceUntilIdle()

        assertTrue(refreshToken?.isEmpty() == true)
        assertTrue(accessToken?.isEmpty() == true)
    }
}
