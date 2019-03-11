package com.example.truongquockhanh.mymovieapp.module

import com.example.truongquockhanh.mymovieapp.feature.popular.ListPopularMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeListPopularMovie(): ListPopularMovieFragment
}