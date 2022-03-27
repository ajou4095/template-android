package com.ray.template.common.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<B : ViewDataBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : DialogFragment() {
    protected lateinit var binding: B
        private set

    var onCancel: (() -> Unit)? = null

    var onConfirm: (() -> Unit)? = null

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
        initWidth()
        initObserver()
    }

    protected open fun initWidth() {
        val maxWidth = Resources.getSystem().displayMetrics.widthPixels
        val width = (maxWidth * 0.9).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    protected open fun initView() = Unit

    protected open fun initObserver() = Unit

    protected fun bind(action: B.() -> Unit) {
        binding.action()
    }
}