package com.example.weatherapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class ResponceErrLiveDataModel: ViewModel() {
    val failtureErrorCode = MutableLiveData<ResponceErrParseDataModel>()
}