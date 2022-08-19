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
    implementation(project(":core"))

    //hilt
    implementation("com.google.dagger:hilt-android:${Versions.Dependency.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Dependency.hilt}")

    //room
    implementation("androidx.room:room-runtime:${Versions.Dependency.room}")
    implementation("androidx.room:room-ktx:${Versions.Dependency.room}")
    kapt("androidx.room:room-compiler:${Versions.Dependency.room}")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.Dependency.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.Dependency.retrofit}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.Dependency.retrofit}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.Dependency.retrofitCoroutineAdapter}")
    implementation("com.github.skydoves:sandwich:${Versions.Dependency.sandwich}")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.Dependency.moshi}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependency.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Dependency.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Dependency.lifecycle}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.Dependency.glide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.Dependency.glide}")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependency.coroutine}")

    //ted permission
    implementation("io.github.ParkSangGwon:tedpermission-coroutine:${Versions.Dependency.tedPermission}")

    //lottie
    implementation("com.airbnb.android:lottie:${Versions.Dependency.lottie}")

    //timber
    implementation("com.jakewharton.timber:timber:${Versions.Dependency.timber}")

    //flipper
    debugImplementation("com.facebook.flipper:flipper:${Versions.Dependency.flipper}")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:${Versions.Dependency.flipper}")
    debugImplementation("com.facebook.flipper:flipper-leakcanary2-plugin:${Versions.Dependency.flipper}")
    debugImplementation("com.facebook.flipper:flipper:${Versions.Dependency.flipper}")
    debugImplementation("com.facebook.soloader:soloader:${Versions.Dependency.flipperSoLoader}")

    //leak canary
    implementation("com.squareup.leakcanary:leakcanary-android:${Versions.Dependency.leakcanary}")

    //androidx
    implementation("androidx.core:core-ktx:${Versions.Dependency.androidXCore}")
    implementation("androidx.appcompat:appcompat:${Versions.Dependency.androidXAppCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.Dependency.androidXConstraintLayout}")
    implementation("androidx.fragment:fragment-ktx:${Versions.Dependency.androidXFragment}")

    implementation("com.google.android.material:material:${Versions.Dependency.androidMaterial}")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.4.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}