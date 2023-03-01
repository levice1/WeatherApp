package com.example.weatherapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse

open class ResponceLiveDataModel: ViewModel() {
    val responceWeatherData = MutableLiveData<JsonWeatherParse>()
}

open class ResponceErrLiveDataModel: ViewModel() {
    val failtureErrorCode = MutableLiveData<ResponceErrDataModel>()
}