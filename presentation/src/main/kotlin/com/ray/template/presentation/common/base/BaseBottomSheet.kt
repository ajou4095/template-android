package com.ray.template.presentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ray.rds.window.alert.AlertDialogFragmentProvider
import com.ray.rds.window.loading.LoadingDialogFragmentProvider
import com.ray.rds.window.snackbar.MessageSnackBar
import com.ray.template.presentation.common.util.coroutine.event.eventObserve
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseBottomSheet<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : BottomSheetDialogFragment() {

    protected abstract val viewModel: BaseViewModel

    private var _binding: B? = null

    protected val binding
        get() = _binding!!

    private var loadingDialog: DialogFragment? = null

    protected val handler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        AlertDialogFragmentProvider.makeAlertDialog(
            title = throwable.javaClass.simpleName,
            message = throwable.message
        ).show()
    }

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

    protected fun showMessageSnackBar(
        anchorView: View? = null,
        message: String? = null,
        @DrawableRes iconRes: Int? = null,
        buttonText: String? = null,
        listener: (() -> Unit)? = null
    ) {
        (dialog?.window?.decorView as? ViewGroup)?.let { parent ->
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
        viewLifecycleOwner.lifecycleScope.launch(handler) {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }
}
