package com.example.weatherapp.json_processing

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
):java.io.Serializable