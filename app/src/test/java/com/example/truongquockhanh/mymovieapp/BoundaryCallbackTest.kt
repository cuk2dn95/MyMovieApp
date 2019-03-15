package com.example.truongquockhanh.mymovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.local.MovieDao
import com.example.truongquockhanh.mymovieapp.data.local.MovieDatabase
import com.example.truongquockhanh.mymovieapp.data.paging.BoundaryCallback
import com.example.truongquockhanh.mymovieapp.data.remote.MovieService
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito

class BoundaryCallbackTest : BaseTestCase() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataBase: MovieDatabase

    @Mock
    lateinit var movieDao: MovieDao

    @Mock
    lateinit var movieService: MovieService

    lateinit var callback: BoundaryCallback

    lateinit var inOrder: InOrder

    @Mock
    lateinit var observer: Observer<NetworkState>

    @Before
    fun init() {
        callback = BoundaryCallback(dataBase, movieService, CompositeDisposable())
        inOrder = Mockito.inOrder(observer)
        Mockito.`when`(dataBase.movieDao()).thenReturn(movieDao)
    }


    @Test
    fun testLoadWhenZeroItemSuccess() {
        callback.networkState.observeForever(observer)
        Mockito.`when`(movieService.getPopularByPage(Mockito.anyInt())).thenReturn(Single.just(fake_result))

        callback.onZeroItemsLoaded()

        Mockito.verify(movieService).getPopularByPage(Mockito.eq(1))
        inOrder.verify(observer).onChanged(NetworkState.LOADING)
        inOrder.verify(observer).onChanged(NetworkState.SUCCESS)
    }

    companion object {
        val fake_movies: List<Movie> = listOf(Movie(page = 1), Movie(page = 1))
        val fake_result = MovieResponse(page = 1, results = fake_movies)
    }

}