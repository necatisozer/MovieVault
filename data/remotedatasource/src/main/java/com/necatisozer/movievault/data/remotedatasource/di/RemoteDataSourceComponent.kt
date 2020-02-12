package com.necatisozer.movievault.data.remotedatasource.di

import dagger.Component

@Component
interface RemoteDataSourceComponent {
    @Component.Factory
    interface Factory {
        fun create(): RemoteDataSourceComponent
    }
}