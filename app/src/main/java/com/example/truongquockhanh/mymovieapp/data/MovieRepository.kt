package com.example.truongquockhanh.mymovieapp.data

import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMoviePagedMovie(page: Int): Single<MovieResponse>

    fun saveMovieInfo(infoMovie: MovieResponse)

    fun saveListMovies(movies : List<Movie>)
}