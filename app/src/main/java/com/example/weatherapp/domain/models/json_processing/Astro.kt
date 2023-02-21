package com.example.weatherapp.domain.models.json_processing

data class Astro(
    val moon_illumination: String,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
):java.io.Serializable