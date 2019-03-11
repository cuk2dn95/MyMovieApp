package com.example.truongquockhanh.mymovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.constant.NetworkState.*
import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.data.paging.PagePopularMovieSource
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito.*

class PagePopularRemoteMovieSourceTest : BaseTestCase() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var netWorkObserver: Observer<NetworkState>

    @Mock
    lateinit var initCallback: PageKeyedDataSource.LoadInitialCallback<Long, Movie>

    @Mock
    lateinit var afterCallBack: PageKeyedDataSource.LoadCallback<Long, Movie>

    @Mock
    lateinit var movieRepo: MovieRepository

    lateinit var sourcePopular: PagePopularMovieSource

    @Mock
    lateinit var initPara: PageKeyedDataSource.LoadInitialParams<Long>


    private lateinit var inOrder: InOrder

    private lateinit var afterPara: PageKeyedDataSource.LoadParams<Long>

    @Before
    fun init() {
        sourcePopular = PagePopularMovieSource(
            movieRepo,
            CompositeDisposable()
        )
        inOrder = inOrder(netWorkObserver)
    }

    @Test
    fun testLoadInitialSuccess() {
        `when`(movieRepo.getPopularMoviePagedMovie(anyInt())).thenReturn(Single.just(fakeResponse))

        sourcePopular.networkState.observeForever(netWorkObserver)
        sourcePopular.loadInitial(initPara, initCallback)

        inOrder.verify(netWorkObserver).onChanged(LOADING)
        inOrder.verify(netWorkObserver).onChanged(SUCCESS)
        verify(initCallback).onResult(fakeResponse.results, 1, 2)
    }

    @Test
    fun testLoadInitialFailed() {
        `when`(movieRepo.getPopularMoviePagedMovie(anyInt()))
            .thenReturn(Single.error(RuntimeException("1 cai loi ngu hoc")))

        sourcePopular.networkState.observeForever(netWorkObserver)
        sourcePopular.loadInitial(initPara, initCallback)

        inOrder.verify(netWorkObserver).onChanged(LOADING)
        inOrder.verify(netWorkObserver).onChanged(ERROR)
    }

    @Test
    fun testLoadAfterSuccess() {
        afterPara = PageKeyedDataSource.LoadParams(2, 5)
        sourcePopular.networkState.observeForever(netWorkObserver)

        `when`(movieRepo.getPopularMoviePagedMovie(anyInt())).thenReturn(
            Single.just(
                fakeResponse
            )
        )

        sourcePopular.loadAfter(afterPara, afterCallBack)

        inOrder.verify(netWorkObserver).onChanged(LOADING)
        inOrder.verify(netWorkObserver).onChanged(SUCCESS)
        verify(afterCallBack).onResult(fakeResponse.results, 3)
    }

    @Test
    fun testLoadExceedPage() {
        afterPara = PageKeyedDataSource.LoadParams(MAX_PAGE.toLong(), 5)
        sourcePopular.networkState.observeForever(netWorkObserver)

        `when`(movieRepo.getPopularMoviePagedMovie(anyInt())).thenReturn(
            Single.just(
                fakeResponse
            )
        )

        sourcePopular.loadAfter(afterPara, afterCallBack)

        inOrder.verify(netWorkObserver).onChanged(LOADING)
        inOrder.verify(netWorkObserver).onChanged(SUCCESS)
        verify(afterCallBack).onResult(fakeResponse.results, null)
    }

    companion object {
        const val MAX_PAGE = 3
        val fakeResponse = MovieResponse().apply {
            page = 1
            results = listOf(Movie())
            totalPages = MAX_PAGE
        }
    }
}