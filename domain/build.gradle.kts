import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

// TODO : Android 종속성 제거
android {
    namespace = "com.ray.template.android.domain"
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
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    testFixtures {
        enable = true
    }
}

dependencies {
    implementation(project(":common"))

    implementation(libs.bundles.kotlin)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.paging.common)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.logging)

    testImplementation(testFixtures(project(":domain")))

    testImplementation(libs.bundles.test)
    kspTest(libs.hilt.android.compiler)

    testFixturesImplementation(libs.bundles.kotlin)
    testFixturesImplementation(libs.hilt.android)
    testFixturesImplementation(libs.bundles.test)
    kspTestFixtures(libs.hilt.android.compiler)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey) ?: System.getenv(propertyKey)
}
