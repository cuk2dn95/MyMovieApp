package com.example.truongquockhanh.mymovieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Movie")
class Movie {
    @field:SerializedName("vote_count")
    var voteCount: Int = 0
    @PrimaryKey
    var id: Int = 0
    var video: Boolean = false
    @field:SerializedName("vote_average")
    var voteAverage: Float = 0f
    var title: String = ""
    var popularity: Float = 0f
    @field:SerializedName("original_language")
    var originalLanguage: String = "en"
    @field:SerializedName("original_title")
    var originalTitle: String = ""
    @field:SerializedName("backdrop_path")
    var backdropPath: String = ""
    @field:SerializedName("poster_path")
    var posterPath: String = ""
    var adult: Boolean = false
    var overview: String = ""
    @field:SerializedName("release_date")
    var releaseDate: String = ""
    var page: Int = 0
}