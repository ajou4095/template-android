plugins {
    id("com.android.application") version Versions.Plugin.gradleBuildTool apply false
    id("com.android.library") version Versions.Plugin.gradleBuildTool apply false
    kotlin("android") version Versions.Plugin.kotlin apply false
    kotlin("kapt") version Versions.Plugin.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.Plugin.hilt apply false
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
