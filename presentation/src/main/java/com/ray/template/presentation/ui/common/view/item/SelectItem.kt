package com.ray.template.presentation.ui.common.view.item

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.ray.template.presentation.R
import com.ray.template.presentation.util.bindingadapter.setOnIntervalClick
import com.ray.template.presentation.util.dp
import com.ray.template.presentation.databinding.ViewSelectItemBinding

class SelectItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {
    private val binding = ViewSelectItemBinding.inflate(LayoutInflater.from(context), this, true)

    var onIntervalClick: OnClickListener? = null
        set(value) {
            field = value
            setOnIntervalClick(field)
        }

    init {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.SelectItem)
        val text = attributes.getText(R.styleable.SelectItem_android_text) ?: ""
        val srcResourceId = attributes.getResourceId(R.styleable.SelectItem_android_src, ResourcesCompat.ID_NULL)
        attributes.recycle()

        val background = TypedValue().apply {
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
        }
        setBackgroundResource(background.resourceId)
        minHeight = 50.dp.toInt()
        maxHeight = 50.dp.toInt()
        isClickable = true

        setText(text)
        setImageResource(srcResourceId)
    }

    fun setText(text: CharSequence) {
        binding.text.text = text
    }

    fun setImageResource(@DrawableRes resId: Int) {
        binding.icon.isVisible = (resId != ResourcesCompat.ID_NULL)
        if (binding.icon.isVisible) {
            binding.icon.setImageResource(resId)
        }
    }
}