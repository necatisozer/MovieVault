package com.necatisozer.movievault.app

import android.app.Application
import com.necatisozer.movievault.data.repository.RepositoryModule
import com.necatisozer.movievault.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        MainActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
