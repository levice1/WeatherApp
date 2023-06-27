package com.example.weatherapp.view_model

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repository.RepositoryImpl
import com.example.weatherapp.model.ErrorDataModel
import com.example.weatherapp.model.ResponceModel
import com.example.weatherapp.model.WeatherDataByCity
import com.example.weatherapp.view_model.util.ParseJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private var city = ""

    val weatherDataByCity = MutableLiveData<WeatherDataByCity>()

    val errorFromServer = MutableLiveData<ErrorDataModel>()

    fun init(inputCityName: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val verifiedCityName = verificationCityField(inputCityName)
            if (verifiedCityName.isNotEmpty()) {
                city = verifiedCityName
                getWeather(context)
            } else {
                errorFromServer.value = ErrorDataModel(
                    403,
                    "Enter the city!"
                )
            }
        }
    }

    private fun getWeather(context: Context) {
        repository.getWeather(city).observe(context as LifecycleOwner) { answer ->
            when (answer) {
                is ResponceModel.Data -> {
                    if (answer.data != null) {
                        val parsedWeatherDataByCity = ParseJson(answer.data).parse(context)
                        weatherDataByCity.value = parsedWeatherDataByCity
                    }
                }

                is ResponceModel.Error -> {
                    if (answer.error != null){
                        errorFromServer.value =
                            ErrorDataModel(answer.error.code, answer.error.message)
                    }
                }
            }
        }
    }

    private fun verificationCityField(inputCityName: String): String {
        return inputCityName
            .lowercase()
            .trim()
            .replace(" ", "+", true)
    }
}