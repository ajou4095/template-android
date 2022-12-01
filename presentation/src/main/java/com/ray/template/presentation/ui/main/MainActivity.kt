package com.ray.template.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ray.template.presentation.databinding.ActivityMainBinding
import com.ray.template.presentation.ui.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        // TODO : Compose + NavigationUI
        bind {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
    }
}