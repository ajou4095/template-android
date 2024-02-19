package com.ray.template.android.presentation.ui.debug

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.PixelFormat
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.ray.template.android.common.orZero
import com.ray.template.android.presentation.R
import kotlin.math.abs


class DebugFloatingButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {

    private val Int.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )

    init {
        setBackgroundResource(R.drawable.ic_launcher)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(40.dp.toInt(), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(40.dp.toInt(), MeasureSpec.EXACTLY)
        )
    }

    companion object {
        private const val DRAG_UP_THRESHOLD = 0.03
        private const val DRAG_DOWN_THRESHOLD = 0.9
        private const val INIT_THRESHOLD_X = -0.55
        private const val INIT_THRESHOLD_Y = 0.65

        @SuppressLint("ClickableViewAccessibility")
        fun addView(
            context: Context
        ) {
            val windowManager = context.getSystemService(WindowManager::class.java)

            val (displayWidthPx, displayHeightPx) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val rect = windowManager.currentWindowMetrics.bounds
                rect.width() to rect.height()
            } else {
                val display = windowManager.defaultDisplay
                display?.width.orZero() to display?.height.orZero()
            }

            val view = DebugFloatingButton(context)
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)

            val layoutParams: WindowManager.LayoutParams =
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        PixelFormat.TRANSLUCENT
                    )
                } else {
                    WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        PixelFormat.TRANSLUCENT
                    )
                }.apply {
                    gravity = Gravity.END or Gravity.TOP
                    x = (view.measuredWidth * INIT_THRESHOLD_X).toInt()
                    y = (displayHeightPx * INIT_THRESHOLD_Y).toInt()
                }

            val dragUpLimit = displayHeightPx * DRAG_UP_THRESHOLD
            val dragDownLimit = displayHeightPx * DRAG_DOWN_THRESHOLD

            var lastAction = 0
            var initialX = 0
            var initialY = 0
            var initialTouchX = 0f
            var initialTouchY = 0f

            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // remember the initial position.
                        initialX = (layoutParams as? WindowManager.LayoutParams)?.x ?: 0
                        initialY = (layoutParams as? WindowManager.LayoutParams)?.y ?: 0
                        // get the touch location
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        lastAction = event.action
                        true
                    }

                    MotionEvent.ACTION_UP -> {
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            context.startActivity(
                                Intent(
                                    context,
                                    DebugActivity::class.java
                                ).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                            )
                        }
                        lastAction = event.action
                        true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val movementX = event.rawX - initialTouchX
                        val movementY = event.rawY - initialTouchY

                        if (abs(movementX) > 1 || abs(movementY) > 1) {
                            val currentY = initialY + (event.rawY - initialTouchY).toInt()
                            if (currentY > dragUpLimit && currentY < dragDownLimit) {
                                layoutParams.y = currentY

                                lastAction = event.action
                                true
                            }
                            false
                        }
                        false
                    }
                }
                false
            }

            windowManager.addView(view, layoutParams)
        }
    }
}
