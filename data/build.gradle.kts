import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.ray.template.android.data"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    buildTypes {
        debug {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(libs.bundles.kotlin)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.bundles.androidx.data)
    implementation(libs.bundles.network)
    ksp(libs.androidx.room.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.logging)
    debugImplementation(libs.okhttp3.logging.interceptor)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey) ?: System.getenv(propertyKey)
}
