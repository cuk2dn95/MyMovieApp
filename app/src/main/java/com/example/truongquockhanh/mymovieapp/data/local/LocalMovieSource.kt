package com.example.truongquockhanh.mymovieapp.data.local

import com.example.truongquockhanh.mymovieapp.common.applySingleSchedulers
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class LocalMovieSource @Inject constructor(private val movieDatabase: MovieDatabase) : MovieRepository {

    private val totalPage: Single<Int>
        get() = movieDatabase.movieDao().getMoviesInformation().map {
            it.totalPages
        }

    override fun saveMovieInfo(infoMovie: MovieResponse) {
        movieDatabase.movieDao().insertMoviesInformation(infoMovie)
    }

    override fun getPopularMoviePagedMovie(page: Int): Single<MovieResponse> {
        return Single.zip<List<Movie>, Int, MovieResponse>(
            movieDatabase.movieDao().getMoviesByPage(page),
            totalPage,
            BiFunction { movies, totalPages ->
                MovieResponse(
                    totalPages = totalPages,
                    results = movies,
                    page = page,
                    totalResults = movies.size
                )
            }).compose(applySingleSchedulers())

    }

    override fun saveListMovies(movies: List<Movie>) {
        movieDatabase.movieDao().insertMovies(movie = *movies.toTypedArray())
    }
}