package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityDetailsWeatherBinding
import com.example.weatherapp.model.json_processing.WeatherParse
import com.example.weatherapp.model.DetailRecViewDataModel

class DetailsWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intentData = intent?.getSerializableExtra("responseData") as WeatherParse
        parseWeatherData(intentData)
        initRecyclerView()
    }

    lateinit var binding: ActivityDetailsWeatherBinding
    lateinit var adapter: DetailsRecyclerViewAdapter
    lateinit var recyclerView:RecyclerView

    var weatherDataList = ArrayList<DetailRecViewDataModel>()

    private fun parseWeatherData(responceData: WeatherParse){
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
        // FEELS LIKE
        val feelsLikeAddText:String
        feelsLikeAddText = if (responceData.current.feelslike_c.toInt() == responceData.current.temp_c.toInt()){
            getString(R.string.feels_like_text_same)
        } else if(responceData.current.feelslike_c.toInt() < responceData.current.temp_c.toInt()){
            getString(R.string.feels_like_text_cooler)
        } else if (responceData.current.feelslike_c.toInt() > responceData.current.temp_c.toInt()){
            getString(R.string.feels_like_text_warmer)
        } else getString(R.string.feels_like_text_same)
        val feelsLike = DetailRecViewDataModel(
            R.drawable.icon_feels_like,getString(R.string.feels_like),responceData.current.feelslike_c.toString() + getString(
                R.string.celsium
            ),feelsLikeAddText)
        weatherDataList.add(feelsLike)
        //WIND SPEED
        val windAddText = when(responceData.current.wind_kph.toInt()){
            in 0..1 -> getString(R.string.wind_Calm)
            in 1..5 -> getString(R.string.wind_LightAir)
            in 6..11 -> getString(R.string.wind_LightBreeze)
            in 12..19 -> getString(R.string.wind_Light)
            in 20..28 -> getString(R.string.wind_Moderate)
            in 29..38 -> getString(R.string.wind_FreshBreeze)
            in 39..49 -> getString(R.string.wind_StrongBreeze)
            in 50..74 -> getString(R.string.wind_Gale)
            in 75..88 -> getString(R.string.wind_StrongGale)
            in 89..117 -> getString(R.string.wind_Storm)
            in 117..200 -> getString(R.string.wind_Hurricane)
            else -> {""}
        }
        val windSpeed = DetailRecViewDataModel(
            R.drawable.icon_wind,getString(R.string.wind_speed),responceData.current.wind_kph.toString()+getString(
                R.string.khp
            ),windAddText)
        weatherDataList.add(windSpeed)
        val humidity = DetailRecViewDataModel(
            R.drawable.icon_humidity,getString(R.string.humidity),responceData.current.humidity.toString() + getString(
                R.string.persent
            ),"")
        weatherDataList.add(humidity)
        //VISIBILITY
        val visAddText = when (responceData.current.vis_km.toInt()){
            in 0..1 -> getString(R.string.visibility_high)
            in 1..3 -> getString(R.string.visibility_middle)
            in 3..7 -> getString(R.string.visibility_low)
            in 7..100 -> getString(R.string.visibility_clear)
            else -> {""}
        }
        val visibility = DetailRecViewDataModel(
            R.drawable.icon_visibility,getString(R.string.visibility),responceData.current.vis_km.toString()+getString(
                R.string.khp
            ),visAddText)
        weatherDataList.add(visibility)
        // PRECIPITATION
        val precAddText = when(responceData.current.precip_mm){
            0.0 -> getString(R.string.precipitation_none)
            in 0.1..1.0 -> getString(R.string.precipitation_low)
            in 1.0..4.0 -> getString(R.string.precipitation_middle)
            in 4.0..12.0 -> getString(R.string.precipitation_high)
            else -> {""}
        }
        val precipitation = DetailRecViewDataModel(
            R.drawable.icon_precippitation,getString(R.string.precipitation),responceData.current.precip_mm.toString()+getString(
                R.string.mm
            ),precAddText)
        weatherDataList.add(precipitation)
        val pressure = DetailRecViewDataModel(
            R.drawable.icon_pressure,getString(R.string.pressure),responceData.current.pressure_mb.toString()+getString(
                R.string.hpa
            ),"")
        weatherDataList.add(pressure)
        //UV INDEX
        val uvIndexAddText:String = when(responceData.current.uv.toInt()){
            in 0..5 -> getString(R.string.uv_low)
            in 5..7 -> getString(R.string.uv_middle)
            in 7..12 ->getString(R.string.uv_high)
            else -> {""}
        }
        val uvIndex = DetailRecViewDataModel(R.drawable.icon_uv,getString(R.string.uv_index),responceData.current.uv.toString(),uvIndexAddText)
        weatherDataList.add(uvIndex)
        val cloudy = DetailRecViewDataModel(
            R.drawable.icon_cloudy,getString(R.string.cloudy),responceData.current.cloud.toString()+getString(
                R.string.persent
            ),"")
        weatherDataList.add(cloudy)


    }

    private fun initRecyclerView(){
        recyclerView = binding.additionalItems
        adapter = DetailsRecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setList(weatherDataList)

    }


}