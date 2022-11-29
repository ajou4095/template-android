import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.Sdk.compile

    defaultConfig {
        applicationId = "com.ray.template"
        minSdk = Versions.Sdk.min
        targetSdk = Versions.Sdk.target
        versionCode = Versions.App.code
        versionName = Versions.App.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".debug"
        }
    }
    /**
     * Gradle 7.0.0 이상에서는 JDK 11 을 기본으로 사용한다.
     * url : https://cliearl.github.io/posts/android/android-gradle-java-11/
     */
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":common"))
    implementation(Dependency.Hilt.hilt)
    kapt(Dependency.Hilt.compiler)

    implementation(Dependency.Room.runtime)
    implementation(Dependency.Room.coroutine)
    kapt(Dependency.Room.compiler)

    implementation(Dependency.Retrofit2.retrofit)
    implementation(Dependency.Retrofit2.Adapter.sandwich)
    implementation(Dependency.Retrofit2.Adapter.coroutine)
    implementation(Dependency.Retrofit2.Converter.moshi)
    implementation(Dependency.Retrofit2.Converter.scalars)

    implementation(Dependency.Lifecycle.viewModel)
    implementation(Dependency.Lifecycle.liveData)

    implementation(Dependency.Glide.glide)
    kapt(Dependency.Glide.compiler)

    implementation(Dependency.Coroutine.core)
    implementation(Dependency.Coroutine.android)

    implementation(Dependency.moshi)
    implementation(Dependency.tedPermission)
    implementation(Dependency.lottie)
    implementation(Dependency.timber)

    debugImplementation(Dependency.Flipper.flipper)
    debugImplementation(Dependency.Flipper.soLoader)
    debugImplementation(Dependency.Flipper.Plugin.network)
    debugImplementation(Dependency.Flipper.Plugin.leakCanary)

    implementation(Dependency.leakCanary)

    implementation(Dependency.AndroidX.core)
    implementation(Dependency.AndroidX.appcompat)
    implementation(Dependency.AndroidX.constraintLayout)
    implementation(Dependency.AndroidX.fragment)
    implementation(Dependency.AndroidX.material)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}
