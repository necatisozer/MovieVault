package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.local.LocalDataSourceModule
import com.necatisozer.movievault.data.source.remote.RemoteDataSourceModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [RemoteDataSourceModule::class, LocalDataSourceModule::class])
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindMovieRepository(prodMovieRepository: ProdMovieRepository): MovieRepository
}