package com.ray.template.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import com.ray.template.common.eventObserve
import com.ray.template.presentation.databinding.ActivityMainBinding
import com.ray.template.presentation.ui.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserver()
    }

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
    }

    override fun initObserver() {
        fun initialize(state: MainState.Init) {
            when (state) {
                MainState.Init.Request -> {
                    viewModel.initialize()
                }
                MainState.Init.Loading -> {
                    showLoading()
                }
                MainState.Init.Success -> {
                    hideLoading()
                }
                is MainState.Init.Fail -> {
                    hideLoading()
                    // TODO : 서버 에러 코드 매칭?
                }
            }
        }

        viewModel.state.eventObserve(this@MainActivity) { state ->
            when (state) {
                is MainState.Init -> {
                    initialize(state)
                }
            }
        }

        viewModel.event.eventObserve(this@MainActivity) { event ->
            when (event) {
                MainViewEvent.Confirm -> {
                    viewModel.viewModelScope.launch {
                        showLoading()
                        delay(1000L)
                        hideLoading()
                        AlertDialogFragmentProvider.makeAlertDialog(
                            title = "Dialog Title",
                            message = "Dialog Message"
                        ).show()
                    }
                }
            }
        }
    }
}
