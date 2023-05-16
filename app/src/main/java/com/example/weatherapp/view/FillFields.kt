package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.view.View
import com.example.weatherapp.databinding.FragmentDetailsWeatherInfoBinding
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.DetailListModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse

class FillFields {

    fun fillDetailsSection(items: DetailListModel, binding: FragmentDetailsWeatherInfoBinding){
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
    fun fillMainSection(responceData: JsonWeatherParse, binding: FragmentMainWeatherInfoBinding){
        // city name
        binding.txtCityName.text = "${responceData.location.name}, ${responceData.location.country}"
        // temperature
        binding.txtTemperature.text = "${responceData.current.temp_c}°C"
        //min temperature now
        binding.txtMinTemperature.text = "Min ${responceData.forecast.forecastday[0].day.mintemp_c}°C"
        //max temperature now
        binding.txtMaxTemperature.text = "Max ${responceData.forecast.forecastday[0].day.maxtemp_c}°C"
        // weather
        binding.txtWeather.text = responceData.current.condition.text
        // date
        val date = responceData.forecast.forecastday[0].date.split("-")
        binding.txtDateNow.text = "${date[2]}.${date[1]}.${date[0]}"
    }


}