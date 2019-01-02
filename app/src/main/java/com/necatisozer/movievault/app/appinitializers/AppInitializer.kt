package com.necatisozer.movievault.app.appinitializers

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}