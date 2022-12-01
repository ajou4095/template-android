package com.ray.template.presentation.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.ray.template.presentation.R
import com.ray.template.presentation.util.getDisplayWidth

abstract class BaseDialogFragment<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : DialogFragment() {
    private var _binding: B? = null

    protected val binding
        get() = _binding!!

    var onCancel: (() -> Unit)? = null

    var onConfirm: (() -> Any)? = null

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
        initWidth()
        initObserver()
    }

    protected open fun initWidth() {
        val maxWidth = getDisplayWidth()
        val width = (maxWidth * 0.8).toInt()

        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_modal)
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun bind(action: B.() -> Unit) {
        binding.action()
    }
}