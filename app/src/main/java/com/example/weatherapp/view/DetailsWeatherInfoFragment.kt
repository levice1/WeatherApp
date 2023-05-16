package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailsWeatherInfoBinding
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.DetailRecViewDataModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse


class DetailsWeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailsWeatherInfoBinding

    private lateinit var adapter: DetailsRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    private var weatherDataList = ArrayList<DetailRecViewDataModel>()

    private val responseForFragsDataModel: ResponceForFragsDataModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = FragmentDetailsWeatherInfoBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responseForFragsDataModel.data.observe(this@DetailsWeatherInfoFragment) {
            parseAdditionalWeatherData(it)
            initRecyclerView()
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = DetailsWeatherInfoFragment()
    }


    private fun parseAdditionalWeatherData(responceData: JsonWeatherParse) {
        // FEELS LIKE
        val feelsLikeAddText =
            if (responceData.current.feelslike_c.toInt() < responceData.current.temp_c.toInt()) {
                getString(R.string.feels_like_text_cooler)
            } else if (responceData.current.feelslike_c.toInt() > responceData.current.temp_c.toInt()) {
                getString(R.string.feels_like_text_warmer)
            } else getString(R.string.feels_like_text_same)
        val feelsLike = DetailRecViewDataModel(
            R.drawable.icon_feels_like,
            getString(R.string.feels_like),
            responceData.current.feelslike_c.toString() + getString(
                R.string.celsium
            ),
            feelsLikeAddText
        )
        weatherDataList.add(feelsLike)
        //WIND SPEED
        val windAddText = when (responceData.current.wind_kph.toInt()) {
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
            else -> {
                ""
            }
        }
        val windSpeed = DetailRecViewDataModel(
            R.drawable.icon_wind,
            getString(R.string.wind_speed),
            responceData.current.wind_kph.toString() + getString(
                R.string.khp
            ),
            windAddText
        )
        weatherDataList.add(windSpeed)
        val humidity = DetailRecViewDataModel(
            R.drawable.icon_humidity,
            getString(R.string.humidity),
            responceData.current.humidity.toString() + getString(
                R.string.persent
            ),
            ""
        )
        weatherDataList.add(humidity)
        //VISIBILITY
        val visAddText = when (responceData.current.vis_km.toInt()) {
            in 0..1 -> getString(R.string.visibility_high)
            in 1..3 -> getString(R.string.visibility_middle)
            in 3..7 -> getString(R.string.visibility_low)
            in 7..100 -> getString(R.string.visibility_clear)
            else -> {
                ""
            }
        }
        val visibility = DetailRecViewDataModel(
            R.drawable.icon_visibility,
            getString(R.string.visibility),
            responceData.current.vis_km.toString() + getString(
                R.string.khp
            ),
            visAddText
        )
        weatherDataList.add(visibility)
        // PRECIPITATION
        val precAddText = when (responceData.current.precip_mm) {
            0.0 -> getString(R.string.precipitation_none)
            in 0.1..1.0 -> getString(R.string.precipitation_low)
            in 1.0..4.0 -> getString(R.string.precipitation_middle)
            in 4.0..12.0 -> getString(R.string.precipitation_high)
            else -> {
                ""
            }
        }
        val precipitation = DetailRecViewDataModel(
            R.drawable.icon_precippitation,
            getString(R.string.precipitation),
            responceData.current.precip_mm.toString() + getString(
                R.string.mm
            ),
            precAddText
        )
        weatherDataList.add(precipitation)
        val pressure = DetailRecViewDataModel(
            R.drawable.icon_pressure,
            getString(R.string.pressure),
            responceData.current.pressure_mb.toString() + getString(
                R.string.hpa
            ),
            ""
        )
        weatherDataList.add(pressure)
        //UV INDEX
        val uvIndexAddText: String = when (responceData.current.uv.toInt()) {
            in 0..5 -> getString(R.string.uv_low)
            in 5..7 -> getString(R.string.uv_middle)
            in 7..12 -> getString(R.string.uv_high)
            else -> {
                ""
            }
        }
        val uvIndex = DetailRecViewDataModel(
            R.drawable.icon_uv,
            getString(R.string.uv_index),
            responceData.current.uv.toString(),
            uvIndexAddText
        )
        weatherDataList.add(uvIndex)
        val cloudy = DetailRecViewDataModel(
            R.drawable.icon_cloudy,
            getString(R.string.cloudy),
            responceData.current.cloud.toString() + getString(
                R.string.persent
            ),
            ""
        )
        weatherDataList.add(cloudy)


    }

    private fun initRecyclerView() {
        recyclerView = binding.additionalItems
        adapter = DetailsRecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setList(weatherDataList)

    }
}