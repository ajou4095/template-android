package com.ray.template.presentation.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : BottomSheetDialogFragment() {
    protected lateinit var binding: B
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    protected fun bind(action: B.() -> Unit) {
        binding.action()
    }
}