package com.example.truongquockhanh.mymovieapp.feature.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.truongquockhanh.mymovieapp.base.BaseViewModel
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.local.MovieDatabase
import com.example.truongquockhanh.mymovieapp.data.paging.BoundaryCallback
import com.example.truongquockhanh.mymovieapp.data.remote.MovieService
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListPopularMovieViewModel @Inject constructor(
    private val database: MovieDatabase,
    private val service: MovieService
) :
    BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var listMovie: LiveData<PagedList<Movie>>
    private val callback = BoundaryCallback(database, service, compositeDisposable)
    override var netWorkState: LiveData<NetworkState> = callback.networkState
    val refreshState: MutableLiveData<NetworkState> = MutableLiveData()

    init {
        val configPaging = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .build()
        listMovie = database.movieDao().getMoviesByPage().toLiveData(configPaging, 1, callback)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onRefresh() {
        refreshData()
    }

    private fun refreshData() {
        refreshState.value = NetworkState.LOADING
        compositeDisposable.add(
            service.getPopularByPage(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({ response ->
                    response.results.forEach { it.page = 1 }
                    database.deleteMovies()
                    database.insertMovies(response.results)
                    refreshState.postValue(NetworkState.SUCCESS)
                }, { refreshState.postValue(NetworkState.ERROR) })
        )
    }


    companion object {
        const val PAGE_SIZE = 20
    }
}