package com.example.weatherapp

import android.view.View
import com.example.weatherapp.databinding.ActivityMainBinding


class VisibilitySetting(var binding: ActivityMainBinding) {
    fun setVisibleAfterGetWeather(){
        binding.mainLayout.setVisibility(View.VISIBLE)
        binding.forecastLayout.setVisibility(View.VISIBLE)
        binding.btnFindCity.setVisibility(View.VISIBLE)
        binding.progressBar.setVisibility(View.INVISIBLE)
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
        binding.mainLayout.setVisibility(View.INVISIBLE)
        binding.forecastLayout.setVisibility(View.INVISIBLE)
    }
}