package com.ray.template.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import com.ray.template.common.repeatOnStarted
import com.ray.template.presentation.databinding.ActivityMainBinding
import com.ray.template.presentation.ui.common.base.BaseActivity
import com.ray.template.presentation.util.coroutine.event.eventObserve
import dagger.hilt.android.AndroidEntryPoint

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

        fun someAction(state: MainState.SomeAction) {
            when (state) {
                MainState.SomeAction.Loading -> {
                    showLoading()
                }
                MainState.SomeAction.Success -> {
                    hideLoading()
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "Dialog Title",
                        message = "Dialog Message"
                    ).show()
                }
                is MainState.SomeAction.Fail -> {
                    hideLoading()
                    AlertDialogFragmentProvider.makeAlertDialog(
                        title = "Dialog Title",
                        message = "Dialog Message"
                    ).show()
                }
            }
        }

        repeatOnStarted {
            viewModel.state.eventObserve { state ->
                when (state) {
                    is MainState.Init -> {
                        initialize(state)
                    }
                    is MainState.SomeAction -> {
                        someAction(state)
                    }
                }
            }
        }

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    MainViewEvent.Confirm -> {
                        viewModel.doSomeAction()
                    }
                }
            }
        }
    }
}
