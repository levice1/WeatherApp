package com.example.weatherapp.view

import android.view.View
import androidx.core.view.marginBottom
import com.example.weatherapp.databinding.ActivityMainBinding


open class VisibilitySetting(var binding: ActivityMainBinding) {
    private val visible = View.VISIBLE
    private val invisible = View.INVISIBLE
    fun setVisibleAfterGetWeather(){
        binding.currentWeatherLayout.visibility = visible
        binding.forecastLayout.visibility = visible
        binding.forecastLayoutInner.visibility = visible
        binding.btnFindCity.visibility = visible
        binding.progressBar.visibility = invisible
        binding.btnFindCity.marginBottom
}
    fun setInvisibleAfterPressBtn(){
        binding.btnFindCity.visibility = invisible
        binding.progressBar.visibility = visible
    }
    fun setInvisibleAfterGetErrCode(){
        binding.btnFindCity.visibility = visible
        binding.progressBar.visibility = invisible
    }

    fun setInvisibleForStart(){
        binding.forecastLayout.visibility = invisible
        binding.forecastLayoutInner.visibility = invisible
    }
}