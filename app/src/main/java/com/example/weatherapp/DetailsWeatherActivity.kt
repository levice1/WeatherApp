package com.example.weatherapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ActivityDetailsWeatherBinding
import com.example.weatherapp.json_processing.WeatherParse
import com.example.weatherapp.recycler_view_details.DetailAdapter
import com.example.weatherapp.recycler_view_details.DetailDataModel

class DetailsWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TestMsg","Start")
        //Thread.sleep(50)
        var intentData = intent?.getSerializableExtra("responseData") as WeatherParse
        Log.d("TestMsg","End: $intentData")
        parseWeatherData(intentData)
        initRecyclerView()
    }

    lateinit var binding: ActivityDetailsWeatherBinding
    lateinit var adapter: DetailAdapter
    lateinit var recyclerView:RecyclerView

    var weatherDataList = ArrayList<DetailDataModel>()

    private fun parseWeatherData(responceData:WeatherParse){
        //now
        // city name
        binding.txtCityName.text = "${responceData.location.name}, ${responceData.location.country}"
        // date
        val date = responceData.forecast.forecastday[0].date.split("-")
        binding.txtDate.text = "${date[2]}.${date[1]}.${date[0]}"
        // temperatura
        binding.txtTemperature.text = "${responceData.current.temp_c}°C"
        //min temperature now
        binding.txtMinTemperature.text = "Min ${responceData.forecast.forecastday[0].day.mintemp_c}°C"
        //max temperature now
        binding.txtMaxTemperature.text = "Max ${responceData.forecast.forecastday[0].day.maxtemp_c}°C"
        // weather
        binding.txtWeather.text = responceData.current.condition.text
        // additional info
        val feelsLike = DetailDataModel(R.drawable.icon_feels_like,getString(R.string.feels_like),responceData.current.feelslike_c.toString() + getString(R.string.celsium),getString(R.string.feels_like_text))
        weatherDataList.add(feelsLike)
        val windSpeed = DetailDataModel(R.drawable.icon_wind,getString(R.string.wind_speed),responceData.current.wind_kph.toString()+getString(R.string.khp),"")
        weatherDataList.add(windSpeed)
        val humidity = DetailDataModel(R.drawable.icon_humidity,getString(R.string.humidity),responceData.current.humidity.toString() + getString(R.string.persent),"")
        weatherDataList.add(humidity)
        val visibility = DetailDataModel(R.drawable.icon_visibility,getString(R.string.visibility),responceData.current.vis_km.toString()+getString(R.string.khp),getString(R.string.visibility_clear))
        weatherDataList.add(visibility)
        val precipitation = DetailDataModel(R.drawable.icon_precippitation,getString(R.string.precipitation),responceData.current.precip_mm.toString()+getString(R.string.mm),getString(R.string.precipitation_low))
        weatherDataList.add(precipitation)
        val pressure = DetailDataModel(R.drawable.icon_pressure,getString(R.string.pressure),responceData.current.pressure_mb.toString()+getString(R.string.hpa),"")
        weatherDataList.add(pressure)
        val uvIndex = DetailDataModel(R.drawable.icon_uv,getString(R.string.uv_index),responceData.current.uv.toString(),"")
        weatherDataList.add(uvIndex)
        val cloudy = DetailDataModel(R.drawable.icon_cloudy,getString(R.string.cloudy),responceData.current.cloud.toString()+getString(R.string.persent),"")
        weatherDataList.add(cloudy)


    }

    private fun initRecyclerView(){
        recyclerView = binding.additionalItems
        adapter = DetailAdapter()
        recyclerView.adapter = adapter
        adapter.setList(weatherDataList)

    }


}