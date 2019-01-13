package com.necatisozer.movievault.ui

import androidx.lifecycle.ViewModelProvider
import com.necatisozer.movievault.ui.main.MainModule
import dagger.Binds
import dagger.Module

@Module(includes = [MainModule::class])
abstract class ActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}