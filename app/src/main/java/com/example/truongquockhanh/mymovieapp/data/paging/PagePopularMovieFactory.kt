package com.example.truongquockhanh.mymovieapp.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.disposables.CompositeDisposable

class PagePopularMovieFactory(
    private val movieRepository: MovieRepository,
    private val localRepository: MovieRepository,
    private val compositeDisposable: CompositeDisposable
) :
    DataSource.Factory<Long, Movie>() {

    val pageMovieSource = MutableLiveData<PagePopularMovieSource>()

    override fun create(): DataSource<Long, Movie> {
        val dataSource = PagePopularMovieSource(
            movieRepository,
            localRepository,
            compositeDisposable
        )
        pageMovieSource.postValue(dataSource)
        return dataSource
    }
}