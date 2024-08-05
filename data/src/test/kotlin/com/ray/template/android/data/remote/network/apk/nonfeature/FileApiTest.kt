package com.ray.template.android.data.remote.network.apk.nonfeature

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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
class FileApiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var scope: TestScope

    @Inject
    lateinit var fileApi: FileApi

    @Before
    fun init() {
        hiltRule.inject()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPreSignedUrlList() = scope.runTest {
        val request1 = fileApi.getPreSignedUrlList(count = 1)
        advanceUntilIdle() // 필요한가?
        assert(request1.isSuccess)

        val request2 = fileApi.getPreSignedUrlList(count = -1)
        advanceUntilIdle() // 필요한가?
        assert(request2.isFailure)

        val request3 = fileApi.getPreSignedUrlList(count = 0)
        advanceUntilIdle() // 필요한가?
        assert(request3.isFailure)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun upload() = scope.runTest {
        val request1 = fileApi.upload(
            preSignedUrl = ApiTestConstant.S3_HOST,
            imageUri = "content://media/external/images/media/33612",
            fileName = "test.jpg"
        )
        advanceUntilIdle() // 필요한가?
        assert(request1.isSuccess)

        val request2 = fileApi.upload(
            preSignedUrl = ApiTestConstant.S3_HOST,
            imageUri = "temp.jpg",
            fileName = "test.jpg"
        )
        advanceUntilIdle() // 필요한가?
        assert(request2.isFailure)
    }
}
