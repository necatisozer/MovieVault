package com.necatisozer.movievault.di

import android.content.Context
import com.necatisozer.movievault.App
import com.necatisozer.movievault.BuildConfig
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @ApplicationContext
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    @CacheDir
    fun provideCacheDir(application: App): File = application.cacheDir

    @Provides
    @TmdbApiKey
    fun provideTmdbApiKey(): String = BuildConfig.TMDB_API_KEY
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CacheDir

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbApiKey