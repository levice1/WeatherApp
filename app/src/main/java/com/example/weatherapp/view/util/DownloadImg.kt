package com.example.weatherapp.view.util

import android.widget.ImageView
import com.example.weatherapp.R
import com.squareup.picasso.Picasso

fun downloadImgFromUrl(url:String, imgView: ImageView) {
    Picasso.get().cancelTag(Any())
    Picasso.get()
        .load("https:$url")
        .placeholder(R.drawable.weather_icon)
        .resize(150, 150)
        .into(imgView)
}