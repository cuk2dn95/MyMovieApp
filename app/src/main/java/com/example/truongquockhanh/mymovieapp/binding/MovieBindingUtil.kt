package com.example.truongquockhanh.mymovieapp.binding

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.truongquockhanh.mymovieapp.BuildConfig
import com.example.truongquockhanh.mymovieapp.R
import com.example.truongquockhanh.mymovieapp.model.Genres
import com.example.truongquockhanh.mymovieapp.model.Language


@BindingAdapter("posterImagePath")
fun setPosterImagePath(view: ImageView, imagePath: String?) {
    Glide.with(view).load(BuildConfig.IMG_URL + imagePath).placeholder(R.drawable.placeholder)
        .override(200, 200)
        .fitCenter()
        .into(view)
}

@BindingAdapter("backPath")
fun setbackPath(view: ImageView, imagePath: String?) {
    if (imagePath == null) return
    Glide.with(view).load(BuildConfig.IMG_URL + imagePath).placeholder(R.drawable.placeholder)
        .centerCrop()
        .signature(ObjectKey(imagePath))
        .into(view)
}

@BindingAdapter("ratingAverage")
fun setRatingAverage(ratingBar: RatingBar, ratingAverage: Float) {
    val rating = ratingAverage * 5 / 10
    ratingBar.rating = rating
}

@BindingAdapter("onClick")
fun setOnClick(view: View, onClick: View.OnClickListener) {
    view.setOnClickListener(onClick)
}

@BindingAdapter("setGenres")
fun setGenres(view: TextView, genres: List<Genres>?) {
    view.text = genres?.joinToString { it.name }
}

@BindingAdapter("setRuntime")
fun setRuntime(view: TextView, runtime: Int?) {
    view.text = runtime.toString() + " m"
}

@BindingAdapter("setLanguage")
fun setLangue(view: TextView, language: List<Language>?) {
    view.text = language?.firstOrNull()?.name
}