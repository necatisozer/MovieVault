package com.necatisozer.movievault.app.appinitializers

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import javax.inject.Inject

class AndroidThreeTenInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        AndroidThreeTen.init(application)
    }
}