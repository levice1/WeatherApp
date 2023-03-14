package com.example.weatherapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse

class ResponceForFragsDataModel:ViewModel() {
    val data: MutableLiveData<JsonWeatherParse> by lazy {
        MutableLiveData<JsonWeatherParse>()
    }
}