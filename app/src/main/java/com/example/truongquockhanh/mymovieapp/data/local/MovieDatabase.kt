package com.example.truongquockhanh.mymovieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse

@Database(entities = [Movie::class, MovieResponse::class], exportSchema = false, version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun getInstance(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "PopularMovie.db")
                .fallbackToDestructiveMigration().build()
        }
    }

}