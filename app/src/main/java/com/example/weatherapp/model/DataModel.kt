package com.example.weatherapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.json_processing.WeatherParse

open class DataModel: ViewModel() {
    val responceWeatherData = MutableLiveData<WeatherParse>()
}