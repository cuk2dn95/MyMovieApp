package com.example.truongquockhanh.mymovieapp.feature.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truongquockhanh.mymovieapp.base.BaseViewModel
import com.example.truongquockhanh.mymovieapp.common.applySingleSchedulers
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.remote.MovieService
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val service: MovieService) : BaseViewModel() {

    override val netWorkState: LiveData<NetworkState>
        get() = _networkState

    private val _networkState = MutableLiveData<NetworkState>()

    val movie = MutableLiveData<Movie>()

    private val compositeDisposable = CompositeDisposable()

    fun getMovieById(id: Int) {
        compositeDisposable.add(
            service.getDetailMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe { _networkState.postValue(NetworkState.LOADING) }
                .subscribe({
                    movie.postValue(it)
                    _networkState.postValue(NetworkState.SUCCESS)
                }, { _networkState.postValue(NetworkState.ERROR) })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}