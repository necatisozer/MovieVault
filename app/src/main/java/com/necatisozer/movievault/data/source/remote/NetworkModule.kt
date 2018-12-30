package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.BuildConfig
import com.necatisozer.movievault.data.source.remote.tmdb.TmdbModule
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(includes = [TmdbModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }
}