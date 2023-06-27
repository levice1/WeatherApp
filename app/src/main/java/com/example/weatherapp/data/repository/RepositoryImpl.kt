package com.example.weatherapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.api_weather.QueryToWeatherApi
import com.example.weatherapp.model.ResponceModel
import com.example.weatherapp.view_model.repository.Repository

class RepositoryImpl: Repository {

    override fun getWeather(city: String): MutableLiveData<ResponceModel> {
        val query = QueryToWeatherApi(city)
        query.getData()
        return query.answerFromServer
    }
}