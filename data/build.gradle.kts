import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
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

    flavorDimensions += "server"
    productFlavors {
        create("development") {
            dimension = "server"
            resValue("string", "server_flag", "development")
        }
        create("production") {
            dimension = "server"
            resValue("string", "server_flag", "production")
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

    testImplementation(testFixtures(project(":data")))
    testImplementation(testFixtures(project(":domain")))

    testImplementation(libs.bundles.test)
    kspTest(libs.hilt.android.compiler)

    testFixturesImplementation(libs.bundles.kotlin)
    testFixturesImplementation(libs.hilt.android)
    testFixturesImplementation(libs.bundles.androidx.data)
    testFixturesImplementation(libs.bundles.network)
    testFixturesImplementation(libs.bundles.test)
    kspTestFixtures(libs.hilt.android.compiler)
}

fun getLocalProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey) ?: System.getenv(propertyKey)
}
