package com.example.weatherapp

data class WeatherParse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)