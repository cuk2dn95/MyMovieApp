package com.example.truongquockhanh.mymovieapp.model.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.truongquockhanh.mymovieapp.model.Movie
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PopularMovie")
class MovieResponse(
    @Ignore
    var page: Int = 0,
    @Ignore
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @PrimaryKey
    @field:SerializedName("total_pages")
    var totalPages: Int = 0,
    @Ignore
    var results: List<Movie> = emptyList()
)