package com.example.truongquockhanh.mymovieapp.feature.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.truongquockhanh.mymovieapp.base.BaseViewModel
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.data.paging.PagePopularMovieFactory
import com.example.truongquockhanh.mymovieapp.data.paging.PagePopularMovieSource
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ListPopularMovieViewModel @Inject constructor(@Named("remote") remoteRepo: MovieRepository) :
    BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dataSource: PagePopularMovieFactory =
        PagePopularMovieFactory(remoteRepo, compositeDisposable)
    var listMovie: LiveData<PagedList<Movie>>

    private val netWorkStateInSource =
        Transformations.switchMap(dataSource.pageMovieSource, PagePopularMovieSource::networkState)

    override var netWorkState: LiveData<NetworkState> = netWorkStateInSource

    init {
        val configPaging = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()

        listMovie = LivePagedListBuilder<Long, Movie>(dataSource, configPaging)
            .build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onRefresh() {
        dataSource.pageMovieSource.value?.invalidate()
    }


    companion object {
        const val PAGE_SIZE = 20
    }
}