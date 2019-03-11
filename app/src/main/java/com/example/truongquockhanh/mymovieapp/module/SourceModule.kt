package com.example.truongquockhanh.mymovieapp.module

import com.example.truongquockhanh.mymovieapp.data.MovieRepository
import com.example.truongquockhanh.mymovieapp.data.local.LocalMovieSource
import com.example.truongquockhanh.mymovieapp.data.remote.RemoteMovieSource
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class SourceModule {

    @Binds
    @Singleton
    @Named("remote")
    abstract fun provideRemoteMovieSource(remoteMovieSource: RemoteMovieSource): MovieRepository


    @Binds
    @Singleton
    @Named("local")
    abstract fun provideLocalMovieSource(localMovieSource: LocalMovieSource): MovieRepository
}