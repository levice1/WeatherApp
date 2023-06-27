package com.example.weatherapp.view_model.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.ResponceModel

interface Repository {
     fun getWeather(city:String): MutableLiveData<ResponceModel>
}