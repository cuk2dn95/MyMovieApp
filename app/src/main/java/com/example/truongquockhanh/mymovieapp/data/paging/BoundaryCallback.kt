package com.example.truongquockhanh.mymovieapp.data.paging

import androidx.paging.PagedList
import com.example.truongquockhanh.mymovieapp.common.createStatusLiveData
import com.example.truongquockhanh.mymovieapp.data.local.MovieDatabase
import com.example.truongquockhanh.mymovieapp.data.remote.MovieService
import com.example.truongquockhanh.mymovieapp.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class BoundaryCallback(
    private val database: MovieDatabase,
    private val service: MovieService,
    private val compositeDisposable: CompositeDisposable
) :
    PagedList.BoundaryCallback<Movie>() {

    private val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
    val networkState = helper.createStatusLiveData()

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { callback ->
            compositeDisposable.add(
                service.getPopularByPage(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe({ response ->
                        response.results.forEach { it.page = response.page }
                        database.insertMovies(response.results)
                        callback.recordSuccess()
                    }, { callback.recordFailure(it) })
            )
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { callback ->
            compositeDisposable.add(
                service.getPopularByPage(itemAtEnd.page + 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe({ response ->
                        response.results.forEach { it.page = response.page }
                        database.insertMovies(response.results)
                        callback.recordSuccess()
                    }, { callback.recordFailure(it) })
            )
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movie) {

    }


}