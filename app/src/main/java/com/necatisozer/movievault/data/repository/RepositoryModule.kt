package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.local.LocalDataSourceModule
import com.necatisozer.movievault.data.source.remote.RemoteDataSourceModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [RepositoryModule.BindsModule::class, RemoteDataSourceModule::class, LocalDataSourceModule::class])
class RepositoryModule {

    @Module
    interface BindsModule {
        @Binds
        @Singleton
        fun bind(prodMovieRepository: ProdMovieRepository): MovieRepository
    }
}