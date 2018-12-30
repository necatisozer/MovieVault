package com.necatisozer.movievault.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.necatisozer.movievault.ui.main.MainViewModel
import com.necatisozer.movievault.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(userViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
