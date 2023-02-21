package com.example.weatherapp.domain

import android.content.Context
import android.content.Intent
import com.example.weatherapp.domain.models.json_processing.WeatherParse
import com.example.weatherapp.presentation.DetailsWeatherActivity



class ShowDetails(private val responseData: WeatherParse, private val context: Context) {

    fun start() {
        val intent = Intent(context, DetailsWeatherActivity::class.java)
        intent.putExtra("responseData", responseData)
        context.startActivity(intent)
    }
}