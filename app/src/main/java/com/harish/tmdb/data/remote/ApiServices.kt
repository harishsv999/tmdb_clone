package com.harish.tmdb.data.remote

import com.harish.tmdb.data.remote.modals.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(): Single<PopularMoviesList>
}
