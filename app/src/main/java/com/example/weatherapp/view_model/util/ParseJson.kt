package com.example.weatherapp.view_model.util

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.model.DetailWeatherData
import com.example.weatherapp.model.DetailWeatherDataModel
import com.example.weatherapp.model.Forecastday
import com.example.weatherapp.model.InputJsonModel
import com.example.weatherapp.model.MainWeatherData
import com.example.weatherapp.model.WeatherDataByCity

class ParseJson(val data: InputJsonModel) {

    private fun getMainData(): MainWeatherData {
        val tempDate = data.forecast.forecastday[0].date.split("-")
        val date = "${tempDate[2]}.${tempDate[1]}.${tempDate[0]}"
        return MainWeatherData(
            city = data.location.name,
            country = data.location.country,
            currentTemp = data.current.temp_c,
            minTemp = data.forecast.forecastday[0].day.mintemp_c,
            maxTemp = data.forecast.forecastday[0].day.maxtemp_c,
            description = data.current.condition.text,
            date
        )
    }

    private fun getDetailsData(context: Context): DetailWeatherData {
        // FEELS LIKE
        val feelsLikeAddText =
            if (data.current.feelslike_c.toInt() < data.current.temp_c.toInt()) {
                context.getString(R.string.feels_like_text_cooler)
            } else if (data.current.feelslike_c.toInt() > data.current.temp_c.toInt()) {
                context.getString(R.string.feels_like_text_warmer)
            } else context.getString(R.string.feels_like_text_same)
        val feelsLike = DetailWeatherDataModel(
            R.drawable.icon_feels_like,
            context.getString(R.string.feels_like),
            data.current.feelslike_c.toString() + context.getString(
                R.string.celsium
            ),
            feelsLikeAddText
        )
        //WIND SPEED
        val windAddText = when (data.current.wind_kph.toInt()) {
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
            data.current.wind_kph.toString() + context.getString(
                R.string.khp
            ),
            windAddText
        )
        val humidity = DetailWeatherDataModel(
            R.drawable.icon_humidity,
            context.getString(R.string.humidity),
            data.current.humidity.toString() + context.getString(
                R.string.persent
            ),
            ""
        )
        //VISIBILITY
        val visAddText = when (data.current.vis_km.toInt()) {
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
            data.current.vis_km.toString() + context.getString(
                R.string.khp
            ),
            visAddText
        )
        // PRECIPITATION
        val precipitationAddText = when (data.current.precip_mm) {
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
            data.current.precip_mm.toString() + context.getString(
                R.string.mm
            ),
            precipitationAddText
        )
        val pressure = DetailWeatherDataModel(
            R.drawable.icon_pressure,
            context.getString(R.string.pressure),
            data.current.pressure_mb.toString() + context.getString(
                R.string.hpa
            ),
            ""
        )
        //UV INDEX
        val uvIndexAddText: String = when (data.current.uv.toInt()) {
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
            data.current.uv.toString(),
            uvIndexAddText
        )

        val cloudy = DetailWeatherDataModel(
            R.drawable.icon_cloudy,
            context.getString(R.string.cloudy),
            data.current.cloud.toString() + context.getString(
                R.string.persent
            ),
            ""
        )

        return DetailWeatherData(
            feelsLike,
            windSpeed,
            humidity,
            visibility,
            precipitation,
            pressure,
            uvIndex,
            cloudy
        )
    }

    private fun getForecastData(): List<Forecastday> {
        return data.forecast.forecastday
    }

    fun parse(context: Context): WeatherDataByCity {
        return WeatherDataByCity(
            getMainData(),
            getDetailsData(context),
            getForecastData()
        )
    }
}