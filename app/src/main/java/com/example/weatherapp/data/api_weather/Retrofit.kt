package com.example.weatherapp.data.api_weather

import com.example.weatherapp.data.util.ApiKeyModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    operator fun invoke(url: String = ApiKeyModel().url): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}