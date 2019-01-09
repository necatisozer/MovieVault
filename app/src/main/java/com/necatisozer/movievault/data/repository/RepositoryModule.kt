package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.rxpaper.RxPaperModule
import com.necatisozer.movievault.data.source.tmdb.TmdbModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [TmdbModule::class, RxPaperModule::class])
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindMovieRepository(prodMovieRepository: ProdMovieRepository): MovieRepository
}