package com.harish.tmdb.domain.repositories

import com.harish.tmdb.data.remote.ApiServices
import com.harish.tmdb.data.remote.modals.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesListRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    MoviesListRepository {

    override fun findPopularMovies(): Single<PopularMoviesList> {
        return apiServices.getPopularMovies()
    }
}

interface MoviesListRepository {
    fun findPopularMovies(): Single<PopularMoviesList>
}