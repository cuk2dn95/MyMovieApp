package com.example.truongquockhanh.mymovieapp.feature.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.truongquockhanh.mymovieapp.R
import com.example.truongquockhanh.mymovieapp.databinding.ItemMovieBinding
import com.example.truongquockhanh.mymovieapp.model.Movie

class ListPopularViewAdapter(private val onMovieClick: (Int) -> Unit) :
    PagedListAdapter<Movie, ListPopularViewAdapter.ViewHolder>(
        DIFF_CALL_BACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position) ?: return
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.root.setOnClickListener { onMovieClick(movie.id) }
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.overview == newItem.overview &&
                        oldItem.adult == newItem.adult &&
                        oldItem.video == newItem.video &&
                        oldItem.backdropPath == newItem.backdropPath &&
                        oldItem.originalLanguage == newItem.originalLanguage &&
                        oldItem.releaseDate == newItem.releaseDate &&
                        oldItem.title == newItem.title &&
                        oldItem.originalTitle == newItem.originalTitle &&
                        oldItem.voteCount == newItem.voteCount &&
                        oldItem.voteAverage == newItem.voteAverage
            }
        }
    }
}