plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Versions.compileSdkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}")

    //androidx
    implementation("androidx.fragment:fragment-ktx:${Versions.androidXFragmentVersion}")
}