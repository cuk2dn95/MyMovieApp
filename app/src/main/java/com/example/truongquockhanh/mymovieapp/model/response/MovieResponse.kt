package com.example.truongquockhanh.mymovieapp.model.response

import com.example.truongquockhanh.mymovieapp.model.Movie


class MovieResponse {
    private var page: Int? = null
    private var totalResults: Int? = null
    private var totalPages: Int? = null
    private var results: List<Movie>? = null
}