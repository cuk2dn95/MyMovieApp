package com.example.truongquockhanh.mymovieapp.binding

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.truongquockhanh.mymovieapp.BuildConfig
import com.example.truongquockhanh.mymovieapp.R


@BindingAdapter("imagePath")
fun setImagePath(view: ImageView, imagePath: String?) {
    Glide.with(view).load(BuildConfig.IMG_URL + imagePath).placeholder(R.drawable.ic_mtrl_chip_checked_circle).fitCenter()
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