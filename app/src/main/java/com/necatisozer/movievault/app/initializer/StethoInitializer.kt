package com.necatisozer.movievault.app.initializer

import android.app.Application
import com.facebook.stetho.Stetho
import javax.inject.Inject

class StethoInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        Stetho.initializeWithDefaults(application)
    }
}