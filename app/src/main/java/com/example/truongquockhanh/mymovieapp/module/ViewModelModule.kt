package com.example.truongquockhanh.mymovieapp.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truongquockhanh.mymovieapp.dagger.ViewModelKey
import com.example.truongquockhanh.mymovieapp.dagger.scope.FragmentScope
import com.example.truongquockhanh.mymovieapp.data.ViewModelFactory
import com.example.truongquockhanh.mymovieapp.feature.detail_movie.DetailMovieViewModel
import com.example.truongquockhanh.mymovieapp.feature.popular.ListPopularMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ViewModelKey(ListPopularMovieViewModel::class)
    @IntoMap
    abstract fun provideListPopularMovieViewModel(viewModel: ListPopularMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = DetailMovieViewModel::class)
    abstract fun provideDetailMovieViewModel(detailMovieViewModel: DetailMovieViewModel): ViewModel
}