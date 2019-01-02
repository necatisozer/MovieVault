package com.necatisozer.movievault.app

import android.app.Application
import android.content.Context
import com.necatisozer.movievault.BuildConfig
import com.necatisozer.movievault.app.appinitializers.AndroidThreeTenInitializer
import com.necatisozer.movievault.app.appinitializers.AppInitializer
import com.necatisozer.movievault.app.appinitializers.TimberInitializer
import com.necatisozer.movievault.utils.Logger
import com.necatisozer.movievault.utils.TimberLogger
import com.necatisozer.movievault.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(
    includes = [
        AppModule.Declerations::class,
        AppModule.Initializers::class,
        ViewModelModule::class
    ]
)
class AppModule {
    @Provides
    @ApplicationContext
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Singleton
    @Provides
    @CacheDir
    fun provideCacheDir(application: Application): File = application.cacheDir

    @Provides
    @TmdbApiKey
    fun provideTmdbApiKey(): String = BuildConfig.TMDB_API_KEY

    @Module
    interface Declerations {
        @Singleton
        @Binds
        fun bindLogger(timberLogger: TimberLogger): Logger
    }

    @Module
    interface Initializers {
        @Binds
        @IntoSet
        fun bindAndroidThreeTenInitializer(androidThreeTenInitializer: AndroidThreeTenInitializer): AppInitializer

        @Binds
        @IntoSet
        fun bindTimberInitializer(timberInitializer: TimberInitializer): AppInitializer
    }
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