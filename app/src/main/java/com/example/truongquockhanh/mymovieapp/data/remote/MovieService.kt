package com.example.truongquockhanh.mymovieapp.data.remote

import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import dagger.internal.GenerationOptions
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularByPage(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movieId}")
    fun getDetailMovie(@Path("movieId") movieId: Int): Single<Movie>
}