package com.ray.template.presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ray.template.presentation.common.theme.TemplateTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    TemplateTheme {
        val navController = rememberNavController()

        Box {

        }
    }
}
