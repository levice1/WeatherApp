package com.example.weatherapp.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse
import com.squareup.picasso.Picasso

class MainWeatherInfoFragment : Fragment() {

lateinit var binding: FragmentMainWeatherInfoBinding
private val responceForFragsDataModel: ResponceForFragsDataModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMainWeatherInfoBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responceForFragsDataModel.data.observe(this@MainWeatherInfoFragment) {
            parseMainWeatherData(it)
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = MainWeatherInfoFragment()
    }


    fun parseMainWeatherData(responceData: JsonWeatherParse) {

        fun getImg(url:String,imgView:ImageView) {
            Picasso.get()
                .load("https:$url") // ссылка на изображение
                .placeholder(R.drawable.weather_icon) // идентификатор ресурса для заглушки
                .resize(150, 150) // размер
                .into(imgView) // куда
        }

        binding.txtCityName.text = "${responceData.location.name}, ${responceData.location.country}"
        // temperatura
        //now
        binding.txtTemperature.text = "${responceData.current.temp_c}°C"
        //2day
        val temp2Day = responceData.forecast.forecastday[1].day.avgtemp_c
        binding.txt2DayTemperature.text = "$temp2Day°C"
        //3day
        val temp3Day = responceData.forecast.forecastday[2].day.avgtemp_c
        binding.txt3DayTemperature.text = "$temp3Day°C"
        //4day
        val temp4Day = responceData.forecast.forecastday[3].day.avgtemp_c
        binding.txt4DayTemperature.text = "$temp4Day°C"
        //5day
        val temp5Day = responceData.forecast.forecastday[4].day.avgtemp_c
        binding.txt5DayTemperature.text = "$temp5Day°C"
        //6day
        val temp6Day = responceData.forecast.forecastday[5].day.avgtemp_c
        binding.txt6DayTemperature.text = "$temp6Day°C"
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
        binding.txt2DayWeather.text = responceData.forecast.forecastday[1].day.condition.text
        //3day
        binding.txt3DayWeather.text = responceData.forecast.forecastday[2].day.condition.text
        //4day
        binding.txt4DayWeather.text = responceData.forecast.forecastday[3].day.condition.text
        //5day
        binding.txt5DayWeather.text = responceData.forecast.forecastday[4].day.condition.text
        //6day
        binding.txt6DayWeather.text = responceData.forecast.forecastday[5].day.condition.text
        //day
        // now
        // date
        val date = responceData.forecast.forecastday[0].date.split("-")
        binding.txtDateNow.text = "${date[2]}.${date[1]}.${date[0]}"
        //2day
        val date2Day = responceData.forecast.forecastday[1].date.split("-")
        binding.txt2DayLabel.text = "${date2Day[2]}-${date2Day[1]}"
        //3day
        val date3Day = responceData.forecast.forecastday[2].date.split("-")
        binding.txt3DayLabel.text = "${date3Day[2]}-${date3Day[1]}"
        //4day
        val date4Day = responceData.forecast.forecastday[3].date.split("-")
        binding.txt4DayLabel.text = "${date4Day[2]}-${date4Day[1]}"
        //5day
        val date5Day = responceData.forecast.forecastday[4].date.split("-")
        binding.txt5DayLabel.text = "${date5Day[2]}-${date5Day[1]}"
        //6day
        val date6Day = responceData.forecast.forecastday[5].date.split("-")
        binding.txt6DayLabel.text = "${date6Day[2]}-${date6Day[1]}"
        //icon
        //2day
        getImg(responceData.forecast.forecastday[1].day.condition.icon,binding.img2Day)
        //3day
        getImg(responceData.forecast.forecastday[2].day.condition.icon,binding.img3Day)
        //4day
        getImg(responceData.forecast.forecastday[3].day.condition.icon,binding.img4Day)
        //5day
        getImg(responceData.forecast.forecastday[4].day.condition.icon,binding.img5Day)
        //6day
        getImg(responceData.forecast.forecastday[5].day.condition.icon,binding.img6Day)
    }
}