package com.jalexy.meme.main.di

import com.jalexy.meme.main.data.api.MemeRepositoryImpl
import com.jalexy.meme.main.domain.MemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideMemeRepository(impl: MemeRepositoryImpl): MemeRepository
}