package com.genxsol.quiltertest.core.common.di

import com.genxsol.quiltertest.core.common.resources.AndroidStringResources
import com.genxsol.quiltertest.core.common.resources.StringResources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    @Binds
    @Singleton
    abstract fun bindStringResources(impl: AndroidStringResources): StringResources
}

