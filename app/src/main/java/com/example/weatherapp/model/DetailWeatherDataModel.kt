package com.example.weatherapp.model



data class DetailListModel(
    val feelsLike:DetailWeatherDataModel,
    val windSpeed:DetailWeatherDataModel,
    val humidity:DetailWeatherDataModel,
    val visibility:DetailWeatherDataModel,
    val precipitation:DetailWeatherDataModel,
    val pressure:DetailWeatherDataModel,
    val uvIndex:DetailWeatherDataModel,
    val cloudy:DetailWeatherDataModel
)
data class DetailWeatherDataModel(val iconId:Int, val labelText:String, val mainData:String, val additionalText:String)