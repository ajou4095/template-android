package com.ray.template.presentation.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ray.rds.window.loading.LoadingDialogFragmentProvider

abstract class BaseBottomSheet<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : BottomSheetDialogFragment() {
    private var _binding: B? = null

    protected val binding
        get() = _binding!!

    private var loadingDialog: DialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun DialogFragment.show() {
        if (
            this@BaseBottomSheet.activity?.isFinishing == false
            && this@BaseBottomSheet.activity?.isDestroyed == false
            && !this@BaseBottomSheet.childFragmentManager.isDestroyed
            && !this@BaseBottomSheet.childFragmentManager.isStateSaved
        ) {
            show(this@BaseBottomSheet.childFragmentManager, javaClass.simpleName)
        }
    }

    protected fun showLoading() {
        if (
            this@BaseBottomSheet.activity?.isFinishing == false
            && this@BaseBottomSheet.activity?.isDestroyed == false
            && !this@BaseBottomSheet.parentFragmentManager.isDestroyed
            && !this@BaseBottomSheet.parentFragmentManager.isStateSaved
            && loadingDialog == null
        ) {
            loadingDialog = LoadingDialogFragmentProvider.makeLoadingDialog()
            loadingDialog?.show()
        }
    }

    protected fun hideLoading() {
        if (
            this@BaseBottomSheet.activity?.isFinishing == false
            && this@BaseBottomSheet.activity?.isDestroyed == false
            && loadingDialog?.parentFragmentManager?.isDestroyed == false
            && loadingDialog?.parentFragmentManager?.isStateSaved == false
            && loadingDialog != null
        ) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    protected fun bind(action: B.() -> Unit) {
        binding.action()
    }
}