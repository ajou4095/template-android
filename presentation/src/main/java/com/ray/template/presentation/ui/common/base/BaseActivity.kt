package com.ray.template.presentation.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.ray.rds.window.loading.LoadingDialogFragmentProvider
import com.ray.rds.window.snackbar.MessageSnackBar

abstract class BaseActivity<B : ViewDataBinding>(
    private val inflater: (LayoutInflater) -> B
) : AppCompatActivity() {
    protected lateinit var binding: B
        private set

    private var loadingDialog: DialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflater(layoutInflater)
        setContentView(binding.root)
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    fun DialogFragment.show() {
        if (
            !this@BaseActivity.isFinishing
            && !this@BaseActivity.isDestroyed
            && !this@BaseActivity.supportFragmentManager.isDestroyed
            && !this@BaseActivity.supportFragmentManager.isStateSaved
        ) {
            show(this@BaseActivity.supportFragmentManager, javaClass.simpleName)
        }
    }

    protected fun showLoading() {
        if (
            !this@BaseActivity.isFinishing
            && !this@BaseActivity.isDestroyed
            && !this@BaseActivity.supportFragmentManager.isDestroyed
            && !this@BaseActivity.supportFragmentManager.isStateSaved
            && loadingDialog == null
        ) {
            loadingDialog = LoadingDialogFragmentProvider.makeLoadingDialog()
            loadingDialog?.show()
        }
    }

    protected fun hideLoading() {
        if (
            !this@BaseActivity.isFinishing
            && !this@BaseActivity.isDestroyed
            && loadingDialog?.parentFragmentManager?.isDestroyed == false
            && loadingDialog?.parentFragmentManager?.isStateSaved == false
            && loadingDialog != null
        ) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    protected fun showMessageSnackBar(
        anchorView: View? = null,
        message: String? = null,
        @DrawableRes iconRes: Int? = null,
        buttonText: String? = null,
        listener: (() -> Unit)? = null
    ) {
        (binding.root as? ViewGroup)?.let { parent ->
            MessageSnackBar.make(
                parent = parent,
                anchorView = anchorView,
                message = message,
                iconRes = iconRes,
                buttonText = buttonText,
                listener = listener
            ).show()
        }
    }

    protected fun bind(action: B.() -> Unit) {
        binding.action()
    }
}
