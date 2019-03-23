package com.necatisozer.movievault.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseViewActivity<M : BaseViewModel, B : ViewDataBinding> :
    BaseActivity<M>() {

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}