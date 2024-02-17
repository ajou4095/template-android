import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.parcelize")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.ray.template.presentation"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    buildTypes {
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(libs.bundles.kotlin)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.bundles.androidx.presentation)
    implementation(libs.google.material)
    implementation(libs.glide)
    ksp(libs.glide.ksp)
    implementation(libs.coil.compose)
    implementation(libs.lottie)
    implementation(libs.shimmer)
    implementation(libs.rds)

    implementation(libs.ted.permission)

    implementation(libs.bundles.logging)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey) ?: System.getenv(propertyKey)
}
