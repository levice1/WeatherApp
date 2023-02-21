package com.example.weatherapp.domain.models.json_processing

data class Forecast(
    val forecastday: List<Forecastday>
):java.io.Serializable