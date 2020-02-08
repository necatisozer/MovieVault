package com.necatisozer.movievault.common.ui.extension

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import splitties.systemservices.layoutInflater

fun <T : ViewDataBinding?> ViewGroup.bindingInflate(@LayoutRes layoutId: Int) =
    DataBindingUtil.inflate<T>(layoutInflater, layoutId, this, false)