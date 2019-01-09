package com.necatisozer.movievault.ui.main

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.ui.main.movielist.MovieListModule
import com.necatisozer.movievault.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [MovieListModule::class])
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
