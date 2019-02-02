package com.necatisozer.movievault.ui.main.moviedetail

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.ui.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailModule {
    @ContributesAndroidInjector
    abstract fun bindMovieDetailFragment(): MovieDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel
}
