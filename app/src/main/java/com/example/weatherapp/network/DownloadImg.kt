package com.example.weatherapp.network

import android.widget.ImageView
import com.example.weatherapp.R
import com.squareup.picasso.Picasso

fun getImgFromUrl(url:String, imgView: ImageView) {
    Picasso.get().cancelTag(Any())
    Picasso.get()
        .load("https:$url") // ссылка на изображение
        .placeholder(R.drawable.weather_icon) // идентификатор ресурса для заглушки
        .resize(150, 150) // размер
        .into(imgView) // куда
}