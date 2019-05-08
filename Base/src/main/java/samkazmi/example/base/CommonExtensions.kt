package samkazmi.example.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


fun Fragment.getColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(requireContext(), color)
}

fun View.closeKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.openKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(
            applicationWindowToken,
            InputMethodManager.SHOW_FORCED, 0
    )
}

fun Fragment.getDrawable(@DrawableRes res: Int) = ContextCompat.getDrawable(requireContext(), res)