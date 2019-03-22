package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.rxpaper.RxPaperModule
import com.necatisozer.movievault.data.source.tmdb.TmdbModule
import com.necatisozer.movievault.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [TmdbModule::class, RxPaperModule::class])
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindMovieRepository(prodMovieRepository: ProdMovieRepository): MovieRepository
}