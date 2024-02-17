# Android-Template

[![Android CI](https://github.com/ajou4095/template-android/actions/workflows/android.yml/badge.svg?branch=main)](https://github.com/ajou4095/template-android/actions/workflows/android.yml)

## Description

Android 개발 시 사용하는 template repository 입니다.
복사 후 template 단어를 전체 검색해, 프로젝트 명으로 변경해주세요. (대소문자 주의해서 폴더까지 변경 부탁드립니다.)

### Build

- Gradle ${GRADLE_VERSION}
- AGP ${PLUGIN_GRADLE_VERSION}
- Kotlin ${KOTLIN_VERSION}
- JDK 17
- Kotlin DSL
- Version Catalog

### Library

- KotlinX
  - Coroutines
  - Serialization
  - DateTime
- AndroidX
  - Room
  - Paging
  - Compose + NavigationUI
- Dagger Hilt
- Ktor
- Coil
- Lottie
- Debug & Log
  - OkHttp3 Logging Interceptor (App Inspection)
  - Leak Canary 2
  - Timber
  - Sentry
