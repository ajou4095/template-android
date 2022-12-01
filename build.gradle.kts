plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    kotlin("android") version "1.7.21" apply false
    kotlin("kapt") version "1.7.21" apply false
    kotlin("plugin.serialization") version "1.7.21" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
