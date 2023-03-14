package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.ResponceErrDataModel
import com.example.weatherapp.model.json_processing.JsonWeatherParse
import com.example.weatherapp.viewmodel.network.InterfaceApi
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RequestToWeatherApi(private val url: String, private val city: String, private val key: String ) {

    fun getData(responseWeatherData: MutableLiveData<JsonWeatherParse>, responceErrCode:MutableLiveData<ResponceErrDataModel>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val interfaceApi = retrofit.create(InterfaceApi::class.java)
        interfaceApi.getData(key, city, 6).enqueue(object : Callback<JsonWeatherParse> {

            override fun onResponse(call: Call<JsonWeatherParse>, response: Response<JsonWeatherParse>) {
                // обработка положительного результата от сервера
                if(response.code()==200) responseWeatherData.postValue(response.body())
                // обработка ошибки от сервера
                else responceErrCode.postValue(ResponceErrDataModel
                    (response.code(),JSONObject(response.errorBody()!!.string())
                    .getJSONObject("error").getString("message")))
            }
            override fun onFailure(call: Call<JsonWeatherParse>, t: Throwable) {
                // обработка ошибки соединения
                responceErrCode.postValue(ResponceErrDataModel(409,"NO INTERNET CONNECTION"))
            }
        })
    }
}
