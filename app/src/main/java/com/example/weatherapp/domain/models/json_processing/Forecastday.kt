package com.example.weatherapp.domain.models.json_processing

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
):java.io.Serializable