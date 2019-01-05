package com.necatisozer.movievault.data.source.rxpaper

import com.pacoworks.rxpaper2.RxPaperBook
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class RxPaperModule {
    @Singleton
    @Provides
    @RxMovieBook
    fun provideMovieBook() = RxPaperBook.with("movie")
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RxMovieBook