package com.necatisozer.movievault.data.source.local.objectbox

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ObjectBoxModule {
    @Singleton
    @Provides
    fun provideBoxStore(application: Application) = BoxStoreManager(application)
}