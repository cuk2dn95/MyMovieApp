package com.example.truongquockhanh.mymovieapp.module

import android.content.Context
import com.example.truongquockhanh.mymovieapp.MainApplication
import com.example.truongquockhanh.mymovieapp.data.local.MovieDatabase
import com.example.truongquockhanh.mymovieapp.data.remote.MovieService
import com.example.truongquockhanh.mymovieapp.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.getInstance()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return RetrofitClient.provideMovieService(retrofit)
    }

    @Provides
    @Singleton
    fun provideAppContext(application: MainApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun providePopularMoviesDb(context: Context): MovieDatabase {
        return MovieDatabase.getInstance(context)
    }
}