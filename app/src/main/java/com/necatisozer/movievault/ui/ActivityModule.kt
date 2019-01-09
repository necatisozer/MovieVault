package com.necatisozer.movievault.ui

import androidx.lifecycle.ViewModelProvider
import com.necatisozer.movievault.ui.main.MainActivityModule
import com.necatisozer.movievault.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [MainActivityModule::class])
abstract class ActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}