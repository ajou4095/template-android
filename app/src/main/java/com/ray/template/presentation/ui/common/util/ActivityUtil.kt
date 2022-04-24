package com.ray.template.presentation.ui.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.ray.template.R

inline fun <reified F : Fragment> FragmentActivity.slideFragment(
    container: FragmentContainerView,
    fragment: F,
    leftToRight: Boolean = true,
    addToBackStack: Boolean = true
) {
    supportFragmentManager.commit {
        val tag = F::class.simpleName
        if (leftToRight) {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        } else {
            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
        replace(container.id, fragment, tag)
        setReorderingAllowed(true)
        if (addToBackStack) addToBackStack(tag)
    }
}