package com.ray.template.presentation.ui.home

sealed class HomeViewEvent {
    data object Confirm : HomeViewEvent()
}
