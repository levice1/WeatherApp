package com.example.weatherapp.view_model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.ResponceModel

interface Repository {
    suspend fun getWeather(city:String): MutableLiveData<ResponceModel>
}