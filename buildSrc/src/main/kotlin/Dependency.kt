object Dependency {
    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Dependency.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Dependency.hilt}"
    }

    object Room {
        const val compiler = "androidx.room:room-compiler:${Versions.Dependency.room}"
        const val runtime = "androidx.room:room-runtime:${Versions.Dependency.room}"
        const val coroutine = "androidx.room:room-ktx:${Versions.Dependency.room}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Dependency.retrofit}"

        object Adapter {
            const val sandwich = "com.github.skydoves:sandwich:${Versions.Dependency.sandwich}"
            const val coroutine =
                "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.Dependency.retrofitCoroutineAdapter}"
        }

        object Converter {
            const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.Dependency.retrofit}"
            const val scalars = "com.squareup.retrofit2:converter-scalars:${Versions.Dependency.retrofit}"
        }
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependency.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Dependency.lifecycle}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Dependency.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.Dependency.glide}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Dependency.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependency.coroutine}"
    }

    object Flipper {
        const val flipper = "com.facebook.flipper:flipper:${Versions.Dependency.flipper}"
        const val soLoader = "com.facebook.soloader:soloader:${Versions.Dependency.flipperSoLoader}"

        object Plugin {
            const val network = "com.facebook.flipper:flipper-network-plugin:${Versions.Dependency.flipper}"
            const val leakCanary = "com.facebook.flipper:flipper-leakcanary2-plugin:${Versions.Dependency.flipper}"
        }
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.Dependency.leakcanary}"

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.Dependency.androidXCore}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Dependency.androidXAppCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Dependency.androidXConstraintLayout}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.Dependency.androidXFragment}"
        const val material = "com.google.android.material:material:${Versions.Dependency.androidMaterial}"
    }

    const val tedPermission = "io.github.ParkSangGwon:tedpermission-coroutine:${Versions.Dependency.tedPermission}"

    const val lottie = "com.airbnb.android:lottie:${Versions.Dependency.lottie}"

    const val timber = "com.jakewharton.timber:timber:${Versions.Dependency.timber}"

    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.Dependency.moshi}"
}