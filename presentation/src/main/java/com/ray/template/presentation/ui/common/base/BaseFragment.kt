package com.ray.template.presentation.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.ray.rds.window.loading.LoadingDialogFragmentProvider

abstract class BaseFragment<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : Fragment() {
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
            this@BaseFragment.activity?.isFinishing == false
            && this@BaseFragment.activity?.isDestroyed == false
            && !this@BaseFragment.childFragmentManager.isDestroyed
            && !this@BaseFragment.childFragmentManager.isStateSaved
        ) {
            show(this@BaseFragment.childFragmentManager, javaClass.simpleName)
        }
    }

    protected fun showLoading() {
        if (
            this@BaseFragment.activity?.isFinishing == false
            && this@BaseFragment.activity?.isDestroyed == false
            && !this@BaseFragment.parentFragmentManager.isDestroyed
            && !this@BaseFragment.parentFragmentManager.isStateSaved
            && loadingDialog == null
        ) {
            loadingDialog = LoadingDialogFragmentProvider.makeLoadingDialog()
            loadingDialog?.show()
        }
    }

    protected fun hideLoading() {
        if (
            this@BaseFragment.activity?.isFinishing == false
            && this@BaseFragment.activity?.isDestroyed == false
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