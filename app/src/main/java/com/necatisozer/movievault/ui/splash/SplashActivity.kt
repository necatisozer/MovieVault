package com.necatisozer.movievault.ui.splash

import android.os.Bundle
import com.necatisozer.movievault.ui.base.BaseActivity
import com.necatisozer.movievault.ui.main.MainActivity
import splitties.activities.start

class SplashActivity : BaseActivity<SplashViewModel>() {
    override val viewModelClass = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start<MainActivity>()
    }
}