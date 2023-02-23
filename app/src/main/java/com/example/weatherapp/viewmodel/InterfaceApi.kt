package com.example.weatherapp.viewmodel

import com.example.weatherapp.model.json_processing.WeatherParse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceApi {
    @GET("v1/forecast.json")
    fun getData(@Query("key") key: String, @Query("q") city: String, @Query("days") days: Int): Call<WeatherParse>
}
