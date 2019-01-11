package com.necatisozer.movievault.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<M : BaseViewModel, B : ViewDataBinding> :
    DaggerAppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: Class<M>

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: M
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}