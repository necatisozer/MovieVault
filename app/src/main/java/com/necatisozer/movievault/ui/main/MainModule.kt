package com.necatisozer.movievault.ui.main

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.ui.ViewModelKey
import com.necatisozer.movievault.ui.main.movielist.MovieListModule
import com.necatisozer.movievault.ui.main.moviesearch.MovieSearchModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [MovieListModule::class, MovieSearchModule::class])
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
