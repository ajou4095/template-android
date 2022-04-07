package com.ray.template.presentation.ui.common.view.button

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.setPadding
import com.ray.template.R
import com.ray.template.common.util.delayOnLifecycle
import com.ray.template.common.util.dp
import com.ray.template.presentation.ui.common.UiCommonContract

class ConfirmButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = androidx.appcompat.R.attr.buttonStyle
) : AppCompatButton(context, attributeSet, defStyle) {

    var onIntervalClick: OnClickListener? = null
        set(value) {
            field = value
            this.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    delayOnLifecycle(UiCommonContract.INTERVAL_CLICK_DURATION) {
                        isClickable = true
                    }
                    value?.onClick(it)
                }
            }
        }

    init {
        elevation = 0f
        gravity = Gravity.CENTER

        minWidth = 0
        minHeight = 0
        minimumWidth = 0
        minimumHeight = 0

        isClickable = true
        isEnabled = true
        isAllCaps = false
        stateListAnimator = null

        setPadding(resources.getDimension(R.dimen.spacing_s).toInt())
        setBackgroundResource(R.drawable.bg_confirm_button)

        setTextSize(TypedValue.COMPLEX_UNIT_PX, 16.dp)
        setTextColor(context.getColor(R.color.blue_grey_50))
        setTypeface(null, Typeface.BOLD)
    }
}