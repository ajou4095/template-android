package com.ray.template.presentation.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.ray.template.presentation.R

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

fun Fragment.registerForActivityResult(
    onFailure: (() -> Unit)? = null,
    onSuccess: (() -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess()
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    getter8: ((bundle: Bundle?) -> T8),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7, param8: T8) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras),
                getter8(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9> Fragment.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    getter8: ((bundle: Bundle?) -> T8),
    getter9: ((bundle: Bundle?) -> T9),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7, param8: T8, param9: T9) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras),
                getter8(result.data?.extras),
                getter9(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun ComponentActivity.registerForActivityResult(
    onFailure: (() -> Unit)? = null,
    onSuccess: (() -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess()
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    getter8: ((bundle: Bundle?) -> T8),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7, param8: T8) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras),
                getter8(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9> ComponentActivity.registerForActivityResult(
    getter1: ((bundle: Bundle?) -> T1),
    getter2: ((bundle: Bundle?) -> T2),
    getter3: ((bundle: Bundle?) -> T3),
    getter4: ((bundle: Bundle?) -> T4),
    getter5: ((bundle: Bundle?) -> T5),
    getter6: ((bundle: Bundle?) -> T6),
    getter7: ((bundle: Bundle?) -> T7),
    getter8: ((bundle: Bundle?) -> T8),
    getter9: ((bundle: Bundle?) -> T9),
    onFailure: (() -> Unit)? = null,
    onSuccess: ((param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7, param8: T8, param9: T9) -> Unit)
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSuccess(
                getter1(result.data?.extras),
                getter2(result.data?.extras),
                getter3(result.data?.extras),
                getter4(result.data?.extras),
                getter5(result.data?.extras),
                getter6(result.data?.extras),
                getter7(result.data?.extras),
                getter8(result.data?.extras),
                getter9(result.data?.extras)
            )
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            onFailure?.invoke()
        }
    }
}