package com.example.weatherapp.view.util

import android.annotation.SuppressLint
import android.view.View
import com.example.weatherapp.databinding.FragmentDetailsWeatherInfoBinding
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.DetailWeatherData
import com.example.weatherapp.model.MainWeatherData

fun fillFieldsInDetailsFragment(items: DetailWeatherData, binding: FragmentDetailsWeatherInfoBinding){
        // FEELS LIKE
        binding.imgLabelFeelsLike.setImageResource(items.feelsLike.iconId)
        binding.txtLabelFeelsLike.text = items.feelsLike.labelText
        binding.txtDataFeelsLike.text = items.feelsLike.mainData
        if ( items.feelsLike.additionalText == "" ) {
            binding.txtDescriptionFeelsLike.visibility = View.GONE
        } else binding.txtDescriptionFeelsLike.text = items.feelsLike.additionalText

        //WIND SPEED
        binding.imgLabelWindSpeed.setImageResource(items.windSpeed.iconId)
        binding.txtLabelwindSpeed.text = items.windSpeed.labelText
        binding.txtDataWindSpeed.text = items.windSpeed.mainData
        if ( items.windSpeed.additionalText == "" ) {
            binding.txtDescriptionWindSpeed.visibility = View.GONE
        } else binding.txtDescriptionWindSpeed.text = items.windSpeed.additionalText

        //VISIBILITY
        binding.imgLabelVisibility.setImageResource(items.visibility.iconId)
        binding.txtLabelVisibility.text = items.visibility.labelText
        binding.txtDataVisibility.text = items.visibility.mainData
        if ( items.visibility.additionalText == "" ) {
            binding.txtDescriptionVisibility.visibility = View.GONE
        } else binding.txtDescriptionVisibility.text = items.visibility.additionalText

        // PRECIPITATION
        binding.imgLabelPrecipitation.setImageResource(items.precipitation.iconId)
        binding.txtLabelPrecipitation.text = items.precipitation.labelText
        binding.txtDataPrecipitation.text = items.precipitation.mainData
        if ( items.precipitation.additionalText == "" ) {
            binding.txtDescriptionPrecipitation.visibility = View.GONE
        } else binding.txtDescriptionPrecipitation.text = items.precipitation.additionalText

        //UV INDEX
        binding.imgLabelUvIndex.setImageResource(items.uvIndex.iconId)
        binding.txtLabelUvIndex.text = items.uvIndex.labelText
        binding.txtDataUvIndex.text = items.uvIndex.mainData
        if ( items.uvIndex.additionalText == "" ) {
            binding.txtDescriptionUvIndex.visibility = View.GONE
        } else binding.txtDescriptionUvIndex.text = items.uvIndex.additionalText

        //PRESSURE
        binding.imgLabelPressure.setImageResource(items.pressure.iconId)
        binding.txtLabelPressure.text = items.pressure.labelText
        binding.txtDataPressure.text = items.pressure.mainData
        if ( items.pressure.additionalText == "" ) {
            binding.txtDescriptionPressure.visibility = View.GONE
        } else binding.txtDescriptionPressure.text = items.pressure.additionalText

        //CLOUDY
        binding.imgLabelCloudy.setImageResource(items.cloudy.iconId)
        binding.txtLabelCloudy.text = items.cloudy.labelText
        binding.txtDataCloudy.text = items.cloudy.mainData
        if ( items.cloudy.additionalText == "" ) {
            binding.txtDescriptionCloudy.visibility = View.GONE
        } else binding.txtDescriptionCloudy.text = items.cloudy.additionalText

        //HUMIDITY
        binding.imgLabelHumidity.setImageResource(items.humidity.iconId)
        binding.txtLabelHumidity.text = items.humidity.labelText
        binding.txtDataHumidity.text = items.humidity.mainData
        if ( items.humidity.additionalText == "" ) {
            binding.txtDescriptionHumidity.visibility = View.GONE
        } else binding.txtDescriptionHumidity.text = items.humidity.additionalText
    }

    @SuppressLint("SetTextI18n")
    fun fillFieldsInMainFragment(weatherData4MainSection: MainWeatherData, binding: FragmentMainWeatherInfoBinding){
        // city name
        binding.txtCityName.text = "${weatherData4MainSection.city}, ${weatherData4MainSection.country}"
        // temperature
        binding.txtTemperature.text = "${weatherData4MainSection.currentTemp}°"
        //min temperature now
        binding.txtMinTemperature.text = "Min ${weatherData4MainSection.minTemp}°"
        //max temperature now
        binding.txtMaxTemperature.text = "Max ${weatherData4MainSection.maxTemp}°"
        // weather
        binding.txtWeather.text = weatherData4MainSection.description
        // date
        binding.txtDateNow.text = weatherData4MainSection.date
    }