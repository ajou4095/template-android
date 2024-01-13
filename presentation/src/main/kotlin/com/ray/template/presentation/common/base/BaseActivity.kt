package com.ray.template.presentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import com.ray.rds.window.loading.LoadingDialogFragmentProvider
import com.ray.rds.window.snackbar.MessageSnackBar
import com.ray.template.presentation.common.util.coroutine.event.eventObserve
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseActivity<B : ViewDataBinding>(
    private val inflater: (LayoutInflater) -> B
) : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: B
        private set

    private var loadingDialog: DialogFragment? = null

    protected val handler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        AlertDialogFragmentProvider.makeAlertDialog(
            title = throwable.javaClass.simpleName,
            message = throwable.message
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflater(layoutInflater)
        setContentView(binding.root)
        initView()
        initObserver()
        observeViewModelError()
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    private fun observeViewModelError() {
        repeatOnStarted {
            viewModel.errorEvent.eventObserve { event ->
                handler.handleException(viewModel.viewModelScope.coroutineContext, event.throwable)
            }
        }
    }

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

    protected fun repeatOnStarted(
        block: suspend CoroutineScope.() -> Unit
    ) {
        this.lifecycleScope.launch(handler) {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }
}
