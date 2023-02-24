package com.example.weatherapp.model

data class ResponceErrorDataModel(
    val code: Int = 0,
    val message: String = "null",
    val status:String = "null"
):java.io.Serializable