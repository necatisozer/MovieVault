package com.necatisozer.movievault.ui

import androidx.lifecycle.ViewModelProvider
import com.necatisozer.movievault.ui.main.MainModule
import com.necatisozer.movievault.ui.splash.SplashModule
import dagger.Binds
import dagger.Module

@Module(includes = [SplashModule::class, MainModule::class])
abstract class ActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}