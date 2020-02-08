package com.necatisozer.movievault

import android.app.Activity
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.necatisozer.movievault.core.di.CoreComponent
import com.necatisozer.movievault.core.di.DaggerCoreComponent
import com.necatisozer.movievault.di.DaggerAppComponent

class MovieVaultApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(coreComponent).inject(this)
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieVaultApplication).coreComponent
    }
}

val Activity.coreComponent get() = MovieVaultApplication.coreComponent(this)