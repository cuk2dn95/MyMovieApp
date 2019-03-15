package com.example.truongquockhanh.mymovieapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse


@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMoviesInformation(infoMoviePopular: MovieResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(vararg movie: Movie)

    @Query("SELECT * FROM Movie WHERE title LIKE :title")
    abstract fun getMoviesByTitle(title: String): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie ORDER BY page ASC")
    abstract fun getMoviesByPage(): DataSource.Factory<Int, Movie>

    @Query("DELETE FROM Movie")
    abstract fun removeAllMovie()
}