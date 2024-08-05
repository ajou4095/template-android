import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ray.template.android.presentation"
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
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        compose = true
    }
    testFixtures {
        enable = true
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    includeSourceInformation = true
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(libs.bundles.kotlin)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.bundles.androidx.presentation)
    implementation(libs.coil.compose)
    implementation(libs.lottie)

    implementation(libs.ted.permission)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.kakao)
    implementation(libs.bundles.logging)
    implementation(libs.androidx.compose.ui.tooling)

//    implementation(testFixtures(project(":common")))
//    implementation(testFixtures(project(":data")))
//    implementation(testFixtures(project(":domain")))
//    implementation(testFixtures(project(":presentation")))
    androidTestImplementation(libs.bundles.test)
    testImplementation(libs.bundles.test)
    kspTest(libs.hilt.android.compiler)
    testFixturesCompileOnly(libs.kotlin)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey) ?: System.getenv(propertyKey)
}
