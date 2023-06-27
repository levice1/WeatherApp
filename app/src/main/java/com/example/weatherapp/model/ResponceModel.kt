package com.example.weatherapp.model

import androidx.lifecycle.MutableLiveData


sealed class ResponceModel{
    data class Data(val data: InputJsonModel? = null) : ResponceModel()
    data class Error(val error: ErrorDataModel? = null) : ResponceModel()
}