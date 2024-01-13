package com.ray.template.presentation.ui.home

import androidx.fragment.app.viewModels
import com.ray.template.presentation.databinding.FragmentHomeBinding
import com.ray.template.presentation.common.base.BaseFragment
import com.ray.template.presentation.common.util.coroutine.event.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@HomeFragment
        }
    }

    override fun initObserver() {

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    HomeViewEvent.Confirm -> {
                        showMessageSnackBar(
                            message = "Confirm Clicked"
                        )
                    }
                }
            }
        }
    }
}
