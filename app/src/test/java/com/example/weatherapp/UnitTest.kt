package com.example.weatherapp
//
//import androidx.lifecycle.MutableLiveData
//import com.example.weatherapp.model.ApiKeyModel
//import com.example.weatherapp.model.ResponceErrDataModel
//import com.example.weatherapp.model.json_processing.JsonWeatherParse
//import com.example.weatherapp.viewmodel.RequestToWeatherApi
//import org.junit.Test
//
//import org.junit.Assert.*
//
//class UnitTest {
//    @Test
//    fun connectToApiIsWork() {
//        val city = "london"
//        val apiKey = ApiKeyModel().getApi()
//        val url = ApiKeyModel().getUrl()
//        val mustBeCity = "London"
//        var actualCity: String? = null
//        val responseLiveWeatherData = MutableLiveData<JsonWeatherParse>()
//        val responseErrLiveWeatherData = MutableLiveData<ResponceErrDataModel>()
//
//        responseLiveWeatherData.observe() { data ->
//            actualCity = data.location.name // Set actualCity to the value of the city returned by the API
//            assertEquals(mustBeCity, actualCity) // Assert that the actualCity matches the expected city
//        }
//
//        RequestToWeatherApi(url, city, apiKey).getData(responseLiveWeatherData, responseErrLiveWeatherData)
//    }
//}