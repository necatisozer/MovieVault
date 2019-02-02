package com.necatisozer.movievault.ui.main.moviesearch

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieSearchModule {
    @ContributesAndroidInjector
    abstract fun bindMovieSearchFragment(): MovieSearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieSearchViewModel::class)
    abstract fun bindMovieSearchViewModel(movieSearchViewModel: MovieSearchViewModel): ViewModel
}
