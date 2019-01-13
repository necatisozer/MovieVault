package com.necatisozer.movievault.app

import android.app.Application
import android.content.Context
import com.necatisozer.movievault.app.initializer.AndroidThreeTenInitializer
import com.necatisozer.movievault.app.initializer.AppInitializer
import com.necatisozer.movievault.app.initializer.RxPaperInitializer
import com.necatisozer.movievault.app.initializer.StethoInitializer
import com.necatisozer.movievault.app.initializer.TimberInitializer
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.helper.TimberLogger
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
        AppModule.Initializers::class
    ]
)
class AppModule {
    @Provides
    fun provideApplication(application: App): Application = application

    @Provides
    @ApplicationContext
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @CacheDir
    fun provideCacheDir(application: Application): File = application.cacheDir

    @Module
    abstract class Declerations {
        @Singleton
        @Binds
        abstract fun bindLogger(timberLogger: TimberLogger): Logger
    }

    @Module
    abstract class Initializers {
        @Binds
        @IntoSet
        abstract fun bindAndroidThreeTenInitializer(androidThreeTenInitializer: AndroidThreeTenInitializer): AppInitializer

        @Binds
        @IntoSet
        abstract fun bindTimberInitializer(timberInitializer: TimberInitializer): AppInitializer

        @Binds
        @IntoSet
        abstract fun bindStethoInitializer(stethoInitializer: StethoInitializer): AppInitializer

        @Binds
        @IntoSet
        abstract fun bindRxPaperInitializer(rxPaperInitializer: RxPaperInitializer): AppInitializer
    }
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CacheDir