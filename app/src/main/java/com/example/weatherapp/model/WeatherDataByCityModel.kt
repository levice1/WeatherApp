package com.example.weatherapp.model

data class WeatherDataByCity(
    val main: MainWeatherData,
    val details: DetailWeatherData,
    val forecast: List<Forecastday>,
)

data class MainWeatherData(
    val city: String,
    val country: String,
    val currentTemp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val date: String
)


data class DetailWeatherData(
    val feelsLike: DetailWeatherDataModel,
    val windSpeed: DetailWeatherDataModel,
    val humidity: DetailWeatherDataModel,
    val visibility: DetailWeatherDataModel,
    val precipitation: DetailWeatherDataModel,
    val pressure: DetailWeatherDataModel,
    val uvIndex: DetailWeatherDataModel,
    val cloudy: DetailWeatherDataModel
)

data class DetailWeatherDataModel(
    val iconId: Int,
    val labelText: String,
    val mainData: String,
    val additionalText: String
)