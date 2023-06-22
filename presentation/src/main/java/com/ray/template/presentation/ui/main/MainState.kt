package com.ray.template.presentation.ui.main

sealed interface MainState {
    sealed interface Init : MainState {
        object Request : Init
        object Loading : Init
        object Success : Init
        data class Fail(val error: Throwable) : Init
    }

    sealed interface SomeAction : MainState {
        object Loading : SomeAction
        object Success : SomeAction
        data class Fail(val error: Throwable) : SomeAction
    }
}
