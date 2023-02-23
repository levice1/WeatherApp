package com.example.weatherapp.presentation

import android.view.View
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.json_processing.WeatherParse

class parseWeatherData(val responceData: WeatherParse, val binding: ActivityMainBinding, private val view:View) {

    fun parse(){

        binding.txtCityName.text = "${responceData.location.name}, ${responceData.location.country}"
        // temperatura
        //now
        binding.txtTemperature.text = "${responceData.current.temp_c}°C"
        //2day
        val temp2Day = responceData.forecast.forecastday[1].day.avgtemp_c
        val txtTemp2DayId = view.findViewById<TextView>(R.id.txt2DayTemperature)
        txtTemp2DayId.text = "$temp2Day°C"
        //3day
        val temp3Day = responceData.forecast.forecastday[2].day.avgtemp_c
        val txtTemp3DayId = view.findViewById<TextView>(R.id.txt3DayTemperature)
        txtTemp3DayId.text = "$temp3Day°C"
        //4day
        val temp4Day = responceData.forecast.forecastday[3].day.avgtemp_c
        val txtTemp4DayId = view.findViewById<TextView>(R.id.txt4DayTemperature)
        txtTemp4DayId.text = "$temp4Day°C"
        //5day
        val temp5Day = responceData.forecast.forecastday[4].day.avgtemp_c
        val txtTemp5DayId = view.findViewById<TextView>(R.id.txt5DayTemperature)
        txtTemp5DayId.text = "$temp5Day°C"
        //6day
        val temp6Day = responceData.forecast.forecastday[5].day.avgtemp_c
        val txtTemp6DayId = view.findViewById<TextView>(R.id.txt6DayTemperature)
        txtTemp6DayId.text = "$temp6Day°C"
        //temperature feels like now
        //!!!!!!!!binding.txtFeelsLike.text = "Feels like ${responceData.current.feelslike_c.toString()}°C"
        //min temperature now
        binding.txtMinTemperature.text = "Min ${responceData.forecast.forecastday[0].day.mintemp_c}°C"
        //max temperature now
        binding.txtMaxTemperature.text = "Max ${responceData.forecast.forecastday[0].day.maxtemp_c}°C"
        // weather
        //now
        binding.txtWeather.text = responceData.current.condition.text
        //2day
        val txtWeather2DayId = view.findViewById<TextView>(R.id.txt2DayWeather)
        txtWeather2DayId.text = responceData.forecast.forecastday[1].day.condition.text
        //3day
        val txtWeather3DayId = view.findViewById<TextView>(R.id.txt3DayWeather)
        txtWeather3DayId.text = responceData.forecast.forecastday[2].day.condition.text
        //4day
        val txtWeather4DayId = view.findViewById<TextView>(R.id.txt4DayWeather)
        txtWeather4DayId.text = responceData.forecast.forecastday[3].day.condition.text
        //5day
        val txtWeather5DayId = view.findViewById<TextView>(R.id.txt5DayWeather)
        txtWeather5DayId.text = responceData.forecast.forecastday[4].day.condition.text
        //6day
        val txtWeather6DayId = view.findViewById<TextView>(R.id.txt6DayWeather)
        txtWeather6DayId.text = responceData.forecast.forecastday[5].day.condition.text
        //day
        // now
        // date
        val date = responceData.forecast.forecastday[0].date.split("-")
        binding.txtDateNow.text = "${date[2]}.${date[1]}.${date[0]}"
        //2day
        val date2Day = responceData.forecast.forecastday[1].date.split("-")
        val txtDate2DayId = view.findViewById<TextView>(R.id.txt2DayLabel)
        txtDate2DayId.text = "${date2Day[2]}-${date2Day[1]}"
        //3day
        val date3Day = responceData.forecast.forecastday[2].date.split("-")
        val txtDate3DayId = view.findViewById<TextView>(R.id.txt3DayLabel)
        txtDate3DayId.text = "${date3Day[2]}-${date3Day[1]}"
        //4day
        val date4Day = responceData.forecast.forecastday[3].date.split("-")
        val txtDate4DayId = view.findViewById<TextView>(R.id.txt4DayLabel)
        txtDate4DayId.text = "${date4Day[2]}-${date4Day[1]}"
        //5day
        val date5Day = responceData.forecast.forecastday[4].date.split("-")
        val txtDate5DayId = view.findViewById<TextView>(R.id.txt5DayLabel)
        txtDate5DayId.text = "${date5Day[2]}-${date5Day[1]}"
        //6day
        val date6Day = responceData.forecast.forecastday[5].date.split("-")
        val txtDate6DayId = view.findViewById<TextView>(R.id.txt6DayLabel)
        txtDate6DayId.text = "${date6Day[2]}-${date6Day[1]}"

    }
}