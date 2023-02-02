package com.example.weatherapp

import com.example.weatherapp.json_processing.WeatherParse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MyApi {
    @GET("v1/forecast.json")
    fun getData(@Query("key") key: String, @Query("q") city: String, @Query("days") days: Int): Call<WeatherParse>
}
