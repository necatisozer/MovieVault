package com.necatisozer.movievault.ui.base

import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController

abstract class BaseFragmentActivity<M : BaseViewModel, B : ViewDataBinding> :
    BaseViewActivity<M, B>() {
    protected abstract val navController: NavController
}