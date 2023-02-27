package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.ResponceErrParseDataModel
import com.example.weatherapp.model.json_processing.WeatherParse
import com.example.weatherapp.viewmodel.network.InterfaceApi
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RequestToApi(val url: String, val city: String, val key: String ) {

    fun getData(responseWeatherData: MutableLiveData<WeatherParse>, responceErrCode:MutableLiveData<ResponceErrParseDataModel>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val interfaceApi = retrofit.create(InterfaceApi::class.java)
        interfaceApi.getData(key, city, 6).enqueue(object : Callback<WeatherParse> {

            override fun onResponse(call: Call<WeatherParse>, response: Response<WeatherParse>) {
                // обработка положительного результата от сервера
                if(response.code()==200) responseWeatherData.postValue(response.body())
                    // обработка ошибки от сервера
                else responceErrCode.postValue(ResponceErrParseDataModel
                    (response.code(),JSONObject(response.errorBody()!!.string())
                    .getJSONObject("error").getString("message")))
            }
            override fun onFailure(call: Call<WeatherParse>, t: Throwable) {
                // обработка ошибки соединения
                responceErrCode.postValue(ResponceErrParseDataModel(409,"NO INTERNET CONNECTION"))
            }
        })
    }

}
