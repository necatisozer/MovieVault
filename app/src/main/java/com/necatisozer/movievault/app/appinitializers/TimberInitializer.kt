package com.necatisozer.movievault.app.appinitializers

import android.app.Application
import com.necatisozer.movievault.BuildConfig
import com.necatisozer.movievault.utils.TimberLogger
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val timberLogger: TimberLogger
) : AppInitializer {
    override fun init(application: Application) {
        timberLogger.setup(BuildConfig.DEBUG)
    }
}