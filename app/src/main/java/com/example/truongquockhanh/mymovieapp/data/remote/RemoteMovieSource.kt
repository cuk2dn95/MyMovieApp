package com.example.truongquockhanh.mymovieapp.data.remote

import com.example.truongquockhanh.mymovieapp.common.applySingleSchedulers
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteMovieSource @Inject constructor(private val movieService: MovieService) : MovieRepository {

    override fun getPopularMoviePagedMovie(page: Int): Single<MovieResponse> {
        return movieService.getPopularByPage(page)
            .doOnSuccess { response -> response.results.forEach { it.page = response.page } }
            .compose(applySingleSchedulers())
    }

    override fun saveMovieInfo(infoMovie: MovieResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveListMovies(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}