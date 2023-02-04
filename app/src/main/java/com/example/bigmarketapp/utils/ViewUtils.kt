package com.example.bigmarketapp.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.bigmarketapp.data.enum.Screens

import timber.log.Timber




fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}


fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun String.startScreen(): Screens {
    return if(this == Screens.SPLASH.name) Screens.SPLASH
    else Screens.MAIN
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.getString(stringResId: Int): String = resources.getString(stringResId)
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }
fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }
fun View.visible(bool: Boolean) {
    if (bool) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}