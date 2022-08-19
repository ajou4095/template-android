import org.gradle.api.tasks.Delete

plugins {
    id("com.android.application") version Versions.Plugin.gradleBuildTool apply false //build gradle tool version
    id("org.jetbrains.kotlin.android") version Versions.Plugin.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.Plugin.hilt apply false
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
