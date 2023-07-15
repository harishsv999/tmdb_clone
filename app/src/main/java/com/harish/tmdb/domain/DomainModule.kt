package com.harish.tmdb.domain

import com.harish.tmdb.domain.repositories.MoviesListRepository
import com.harish.tmdb.domain.repositories.MoviesListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun getMoviesListRepository(repositoryImpl: MoviesListRepositoryImpl): MoviesListRepository
}