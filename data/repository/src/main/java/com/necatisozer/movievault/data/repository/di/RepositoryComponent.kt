package com.necatisozer.movievault.data.repository.di

import com.necatisozer.movievault.data.localdatasource.di.LocalDataSourceComponent
import com.necatisozer.movievault.data.remotedatasource.di.RemoteDataSourceComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [RemoteDataSourceComponent::class, LocalDataSourceComponent::class])
interface RepositoryComponent {
    @Component.Factory
    interface Factory {
        fun create(
            remoteDataSourceComponent: RemoteDataSourceComponent,
            localDataSourceComponent: LocalDataSourceComponent
        ): RepositoryComponent
    }
}