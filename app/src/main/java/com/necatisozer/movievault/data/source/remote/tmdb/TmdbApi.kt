package com.necatisozer.movievault.data.source.remote.tmdb

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    fun getPopularMovies(@Query("page") page: Int): Single<ResponseBody>
}