package com.necatisozer.movievault.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

operator fun ViewGroup.plusAssign(child: View) = addView(child)
operator fun ViewGroup.minusAssign(child: View) = removeView(child)

fun ViewGroup.inflate(@LayoutRes resourceId: Int): View =
    LayoutInflater.from(context).inflate(resourceId, this, false)

fun <T : ViewDataBinding?> ViewGroup.bindingInflate(@LayoutRes resourceId: Int) =
    DataBindingUtil.inflate<T>(
        LayoutInflater.from(context),
        resourceId,
        this,
        false
    )

inline fun ViewGroup.forEach(action: (view: View) -> Unit) {
    for (index in 0 until childCount) {
        action(getChildAt(index))
    }
}