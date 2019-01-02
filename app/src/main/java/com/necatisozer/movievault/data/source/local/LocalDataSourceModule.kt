package com.necatisozer.movievault.data.source.local

import com.necatisozer.movievault.data.source.local.objectbox.ObjectBoxModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ObjectBoxModule::class])
interface LocalDataSourceModule {
    @Singleton
    @Binds
    fun bindLocalDataSource(prodLocalDataSource: ProdLocalDataSource): LocalDataSource
}