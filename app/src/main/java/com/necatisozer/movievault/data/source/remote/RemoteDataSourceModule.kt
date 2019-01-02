package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.remote.tmdb.TmdbModule
import com.necatisozer.movievault.utils.debug
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(includes = [TmdbModule::class, RemoteDataSourceModule.BindsModule::class])
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        debug { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Module
    interface BindsModule {
        @Singleton
        @Binds
        fun bindRemoteDataSource(prodRemoteDataSource: ProdRemoteDataSource): RemoteDataSource
    }
}