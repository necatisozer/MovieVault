package com.necatisozer.movievault.utils

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.necatisozer.movievault.BuildConfig

operator fun ViewGroup.plusAssign(child: View) = addView(child)
operator fun ViewGroup.minusAssign(child: View) = removeView(child)

inline fun debug(body: () -> Unit) {
    if (BuildConfig.DEBUG) body()
}

inline fun targetApi(version: Int, body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= version) body.invoke()
}

// hashCode() for Nullable types
fun Any?.hashCode(): Int = this?.hashCode() ?: 0

inline fun EditText.afterTextChanged(crossinline listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Do nothing
        }
    })
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}