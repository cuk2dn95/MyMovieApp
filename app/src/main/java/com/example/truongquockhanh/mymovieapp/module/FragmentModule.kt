package com.example.truongquockhanh.mymovieapp.module

import com.example.truongquockhanh.mymovieapp.dagger.scope.FragmentScope
import com.example.truongquockhanh.mymovieapp.feature.detail_movie.DetailMovieFragment
import com.example.truongquockhanh.mymovieapp.feature.popular.ListPopularMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeListPopularMovie(): ListPopularMovieFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeDetailModue(): DetailMovieFragment
}