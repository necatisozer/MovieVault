package com.necatisozer.movievault.app

import android.app.Activity
import android.app.Application
import com.necatisozer.movievault.app.appinitializers.AppInitializer
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var appInitializers: Set<@JvmSuppressWildcards AppInitializer>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        appInitializers.forEach { it.init(this) }
    }

    override fun activityInjector() = dispatchingAndroidInjector
}