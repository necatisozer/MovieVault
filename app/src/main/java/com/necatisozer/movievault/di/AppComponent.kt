package com.necatisozer.movievault.di

import com.necatisozer.movievault.MovieVaultApplication
import com.necatisozer.movievault.core.di.CoreComponent
import com.necatisozer.movievault.core.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(application: MovieVaultApplication)
}