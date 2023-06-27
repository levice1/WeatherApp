package com.example.weatherapp.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repository.RepositoryImpl
import com.example.weatherapp.model.ResponceModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val repository = RepositoryImpl()

    fun getWeather(city: String, owner: LifecycleOwner){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getWeather(city).observe(owner){ answer ->
                when(answer){
                    is ResponceModel.Data -> {
                        // когда удача
                    }
                    is ResponceModel.Error -> {
                        // когда ошибка
                    }
                }
            }
        }
    }
}