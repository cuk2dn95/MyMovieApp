package com.example.truongquockhanh.mymovieapp.feature.popular

import androidx.lifecycle.ViewModel
import com.example.truongquockhanh.mymovieapp.dagger.ViewModelKey
import com.example.truongquockhanh.mymovieapp.dagger.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListPopularMovieModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(value = ListPopularMovieViewModel::class)
    abstract fun provideListPopularMovieViewModel(viewModel: ListPopularMovieViewModel): ViewModel

}