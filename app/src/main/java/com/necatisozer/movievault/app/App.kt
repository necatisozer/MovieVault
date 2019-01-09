package com.necatisozer.movievault.app

import com.necatisozer.movievault.app.appinitializers.AppInitializer
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {
    @Inject
    lateinit var appInitializers: Set<@JvmSuppressWildcards AppInitializer>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        appInitializers.forEach { it.init(this) }
    }
}