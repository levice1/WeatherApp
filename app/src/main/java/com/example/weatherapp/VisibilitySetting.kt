package com.example.weatherapp

import android.view.View
import androidx.core.view.marginBottom
import com.example.weatherapp.databinding.ActivityMainBinding


class VisibilitySetting(var binding: ActivityMainBinding) {
    fun setVisibleAfterGetWeather(){
        binding.currentWeatherLayout.setVisibility(View.VISIBLE)
        binding.forecastLayout.setVisibility(View.VISIBLE)
        binding.btnFindCity.setVisibility(View.VISIBLE)
        binding.progressBar.setVisibility(View.INVISIBLE)
        binding.btnFindCity.marginBottom
}
    fun setInvisibleAfterPressBtn(){
        binding.btnFindCity.setVisibility(View.INVISIBLE)
        binding.progressBar.setVisibility(View.VISIBLE)
    }
    fun setInvisibleAfterGetErrCode(){
        binding.btnFindCity.setVisibility(View.VISIBLE)
        binding.progressBar.setVisibility(View.INVISIBLE)
    }
    fun setInvisibleForStart(){
        binding.currentWeatherLayout.setVisibility(View.INVISIBLE)
        binding.forecastLayout.setVisibility(View.INVISIBLE)
    }
}