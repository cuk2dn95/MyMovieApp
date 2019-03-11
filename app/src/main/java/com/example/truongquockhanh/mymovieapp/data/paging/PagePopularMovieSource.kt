package com.example.truongquockhanh.mymovieapp.data.paging


import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.disposables.CompositeDisposable

class PagePopularMovieSource(
    private val remoteRepository: MovieRepository,
    private val localRepository: MovieRepository,
    private val compositeDisposable: CompositeDisposable
) :
    PageKeyedDataSource<Long, Movie>() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: MutableLiveData<NetworkState> = _networkState

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Movie>) {
        compositeDisposable.add(remoteRepository.getPopularMoviePagedMovie(1)
            .doOnSubscribe { _networkState.postValue(NetworkState.LOADING) }
            .subscribe({ result ->
                _networkState.postValue(NetworkState.SUCCESS)
                callback.onResult(result.results, result.page.toLong(), result.page + 1L)
            }, { _networkState.postValue(NetworkState.ERROR) })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        compositeDisposable.add(
            remoteRepository.getPopularMoviePagedMovie(params.key.toInt())
                .doOnSubscribe { _networkState.postValue(NetworkState.LOADING) }
                .subscribe({ result ->
                    _networkState.postValue(NetworkState.SUCCESS)
                    callback.onResult(
                        result.results,
                        if (params.key + 1 > result.totalPages) null else params.key + 1
                    )
                }, { _networkState.postValue(NetworkState.ERROR) })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {

    }
}