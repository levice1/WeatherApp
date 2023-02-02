package com.example.weatherapp.json_processing

data class WeatherParse(
    val current: Current,
    val forecast: Forecast,
    val location: Location,
    val error: Error
)