package com.necatisozer.movievault.data.source.local

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module()
interface LocalDataSourceModule {
    @Singleton
    @Binds
    fun bindLocalDataSource(prodLocalDataSource: ProdLocalDataSource): LocalDataSource
}