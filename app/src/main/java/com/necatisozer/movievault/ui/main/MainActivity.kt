package com.necatisozer.movievault.ui.main

import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MainActivityBinding
import com.necatisozer.movievault.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, MainActivityBinding>(MainViewModel::class.java) {
    override fun getLayoutRes() = R.layout.main_activity
}
