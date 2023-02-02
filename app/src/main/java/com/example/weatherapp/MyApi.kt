package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MyApi {

    fun getRequest(city: String) = "v1/forecast.json?key=a2c054487d1040eb8fe145528232601&q=$city&days=4"
    @GET("v1/forecast.json")
    fun getData(@Query("key") key: String, @Query("q") city: String, @Query("days") days: Int): Call<WeatherParse>
}
