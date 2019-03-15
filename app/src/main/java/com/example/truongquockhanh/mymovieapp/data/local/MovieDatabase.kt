package com.example.truongquockhanh.mymovieapp.data.local

import android.content.Context
import androidx.paging.DataSource
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import java.util.concurrent.Executors

@Database(entities = [Movie::class, MovieResponse::class], exportSchema = false, version = 2)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    fun insertMovies(list: List<Movie>) {
        IO_NETWORK.execute {
            movieDao().insertMovies(*list.toTypedArray())
        }
    }

    fun deleteMovies() {
        IO_NETWORK.execute {
            movieDao().removeAllMovie()
        }
    }

    companion object {
        private val IO_NETWORK = Executors.newSingleThreadExecutor()
        fun getInstance(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "PopularMovie.db")
                .fallbackToDestructiveMigration().build()
        }
    }

}