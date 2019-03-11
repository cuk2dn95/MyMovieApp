package com.example.truongquockhanh.mymovieapp.data.remote

import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularByPage(@Query("page") page: Int): Single<MovieResponse>
}