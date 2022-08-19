plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdkVersion

    defaultConfig {
        applicationId = "com.ray.template"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = Versions.versionCode
        versionName = Versions.versionName

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
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}")

    //room
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    implementation("androidx.room:room-ktx:${Versions.roomVersion}")
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.retrofitVersion}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutineAdapterVersion}")
    implementation("com.github.skydoves:sandwich:${Versions.sandwichVersion}")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glideVersion}")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}")

    //ted permission
    implementation("io.github.ParkSangGwon:tedpermission-coroutine:${Versions.tedPermissionVersion}")

    //lottie
    implementation("com.airbnb.android:lottie:${Versions.lottieVersion}")

    //timber
    implementation("com.jakewharton.timber:timber:${Versions.timberVersion}")

    //flipper
    debugImplementation("com.facebook.flipper:flipper:${Versions.flipperVersion}")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:${Versions.flipperVersion}")
    debugImplementation("com.facebook.flipper:flipper-leakcanary2-plugin:${Versions.flipperVersion}")
    debugImplementation("com.facebook.flipper:flipper:${Versions.flipperVersion}")
    debugImplementation("com.facebook.soloader:soloader:${Versions.flipperSoLoaderVersion}")

    //leak canary
    implementation("com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}")

    //androidx
    implementation("androidx.core:core-ktx:${Versions.androidXCoreVersion}")
    implementation("androidx.appcompat:appcompat:${Versions.androidXAppCompatVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayoutVersion}")
    implementation("androidx.fragment:fragment-ktx:${Versions.androidXFragmentVersion}")

    implementation("com.google.android.material:material:${Versions.androidMaterialVersion}")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.4.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}