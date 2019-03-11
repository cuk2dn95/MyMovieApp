package com.example.truongquockhanh.mymovieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.example.truongquockhanh.mymovieapp.model.response.MovieResponse
import io.reactivex.Single


@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMoviesInformation(infoMoviePopular : MovieResponse)

    @Query("SELECT * FROM PopularMovie")
    abstract fun getMoviesInformation() : Single<MovieResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(vararg movie: Movie)

    @Query("SELECT * FROM Movie WHERE title LIKE :title")
    abstract fun getMoviesByTitle(title: String): Single<List<Movie>>

    @Query("SELECT * FROM Movie LIMIT :page,20")
    abstract fun getMoviesByPage(page: Int): Single<List<Movie>>

    @Query("DELETE FROM Movie")
    abstract fun removeAllMovie()
}