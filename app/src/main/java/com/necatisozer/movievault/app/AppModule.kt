package com.necatisozer.movievault.app

import android.app.Application
import android.content.Context
import com.necatisozer.movievault.app.initializer.AppInitializerModule
import com.necatisozer.movievault.data.repository.RepositoryModule
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.helper.TimberLogger
import com.necatisozer.movievault.ui.ActivityModule
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(
    includes = [
        AppInitializerModule::class,
        ActivityModule::class,
        RepositoryModule::class
    ]
)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindApplication(application: App): Application

    @Singleton
    @Binds
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context

    @Singleton
    @Binds
    abstract fun bindLogger(timberLogger: TimberLogger): Logger
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext