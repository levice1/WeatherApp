package com.example.weatherapp.viewmodel

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.model.DetailListModel
import com.example.weatherapp.model.DetailWeatherDataModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse

class ParseDetailWeatherData {

    fun getDetails(responceData: JsonWeatherParse, context: Context): DetailListModel {
            // FEELS LIKE
            val feelsLikeAddText =
                if (responceData.current.feelslike_c.toInt() < responceData.current.temp_c.toInt()) {
                    context.getString(R.string.feels_like_text_cooler)
                } else if (responceData.current.feelslike_c.toInt() > responceData.current.temp_c.toInt()) {
                    context.getString(R.string.feels_like_text_warmer)
                } else context.getString(R.string.feels_like_text_same)
            val feelsLike = DetailWeatherDataModel(
                R.drawable.icon_feels_like,
                context.getString(R.string.feels_like),
                responceData.current.feelslike_c.toString() + context.getString(
                    R.string.celsium
                ),
                feelsLikeAddText
            )
        //WIND SPEED
            val windAddText = when (responceData.current.wind_kph.toInt()) {
                in 0..1 -> context.getString(R.string.wind_Calm)
                in 1..5 -> context.getString(R.string.wind_LightAir)
                in 6..11 -> context.getString(R.string.wind_LightBreeze)
                in 12..19 -> context.getString(R.string.wind_Light)
                in 20..28 -> context.getString(R.string.wind_Moderate)
                in 29..38 -> context.getString(R.string.wind_FreshBreeze)
                in 39..49 -> context.getString(R.string.wind_StrongBreeze)
                in 50..74 -> context.getString(R.string.wind_Gale)
                in 75..88 -> context.getString(R.string.wind_StrongGale)
                in 89..117 -> context.getString(R.string.wind_Storm)
                in 117..200 -> context.getString(R.string.wind_Hurricane)
                else -> {
                    ""
                }
            }
            val windSpeed = DetailWeatherDataModel(
                R.drawable.icon_wind,
                context.getString(R.string.wind_speed),
                responceData.current.wind_kph.toString() + context.getString(
                    R.string.khp
                ),
                windAddText
            )
            val humidity = DetailWeatherDataModel(
                R.drawable.icon_humidity,
                context.getString(R.string.humidity),
                responceData.current.humidity.toString() + context.getString(
                    R.string.persent
                ),
                ""
            )
            //VISIBILITY
            val visAddText = when (responceData.current.vis_km.toInt()) {
                in 0..1 -> context.getString(R.string.visibility_high)
                in 1..3 -> context.getString(R.string.visibility_middle)
                in 3..7 -> context.getString(R.string.visibility_low)
                in 7..100 -> context.getString(R.string.visibility_clear)
                else -> {
                    ""
                }
            }
            val visibility = DetailWeatherDataModel(
                R.drawable.icon_visibility,
                context.getString(R.string.visibility),
                responceData.current.vis_km.toString() + context.getString(
                    R.string.khp
                ),
                visAddText
            )
            // PRECIPITATION
            val precAddText = when (responceData.current.precip_mm) {
                0.0 -> context.getString(R.string.precipitation_none)
                in 0.1..1.0 -> context.getString(R.string.precipitation_low)
                in 1.0..4.0 -> context.getString(R.string.precipitation_middle)
                in 4.0..12.0 -> context.getString(R.string.precipitation_high)
                else -> {
                    ""
                }
            }
            val precipitation = DetailWeatherDataModel(
                R.drawable.icon_precippitation,
                context.getString(R.string.precipitation),
                responceData.current.precip_mm.toString() + context.getString(
                    R.string.mm
                ),
                precAddText
            )
            val pressure = DetailWeatherDataModel(
                R.drawable.icon_pressure,
                context.getString(R.string.pressure),
                responceData.current.pressure_mb.toString() + context.getString(
                    R.string.hpa
                ),
                ""
            )
            //UV INDEX
            val uvIndexAddText: String = when (responceData.current.uv.toInt()) {
                in 0..5 -> context.getString(R.string.uv_low)
                in 5..7 -> context.getString(R.string.uv_middle)
                in 7..12 -> context.getString(R.string.uv_high)
                else -> {
                    ""
                }
            }
            val uvIndex = DetailWeatherDataModel(
                R.drawable.icon_uv,
                context.getString(R.string.uv_index),
                responceData.current.uv.toString(),
                uvIndexAddText
            )

            val cloudy = DetailWeatherDataModel(
                R.drawable.icon_cloudy,
                context.getString(R.string.cloudy),
                responceData.current.cloud.toString() + context.getString(
                    R.string.persent
                ),
                ""
            )

        return DetailListModel(feelsLike, windSpeed, humidity, visibility, precipitation, pressure, uvIndex, cloudy)
    }

}