package com.example.weatherapp.model.json_processing

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
):java.io.Serializable