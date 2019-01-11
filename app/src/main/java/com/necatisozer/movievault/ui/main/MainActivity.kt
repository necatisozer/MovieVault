package com.necatisozer.movievault.ui.main

import com.necatisozer.movievault.R
import com.necatisozer.movievault.databinding.MainActivityBinding
import com.necatisozer.movievault.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, MainActivityBinding>() {
    override val layoutRes = R.layout.main_activity
    override val viewModelClass = MainViewModel::class.java
}
