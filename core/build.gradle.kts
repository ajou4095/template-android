plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Versions.Sdk.compile

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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependency.coroutine}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependency.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Dependency.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Dependency.lifecycle}")

    //androidx
    implementation("androidx.fragment:fragment-ktx:${Versions.Dependency.androidXFragment}")
}