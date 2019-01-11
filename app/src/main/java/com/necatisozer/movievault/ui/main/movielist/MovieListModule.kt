package com.necatisozer.movievault.ui.main.movielist

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieListModule {
    @ContributesAndroidInjector
    abstract fun bindMovieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel
}
