package com.necatisozer.movievault.data.localdatasource.di

import dagger.Component

@Component
interface LocalDataSourceComponent {
    @Component.Factory
    interface Factory {
        fun create(): LocalDataSourceComponent
    }
}