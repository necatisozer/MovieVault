package com.necatisozer.movievault.ui.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding

abstract class BaseToolbarActivity<M : BaseViewModel, B : ViewDataBinding> :
    BaseFragmentActivity<M, B>() {
    protected abstract val toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }
}