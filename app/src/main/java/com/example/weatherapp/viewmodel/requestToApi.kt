package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModelStore
import com.example.weatherapp.model.DataModel
import com.example.weatherapp.model.json_processing.WeatherParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestToApi(val url: String, val city: String, val key: String) {
    private val responseWeatherData: DataModel by ViewModelStore
    fun getData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val interfaceApi = retrofit.create(InterfaceApi::class.java)
        interfaceApi.getData(key, city, 6).enqueue(object : Callback<WeatherParse> {
            override fun onResponse(call: Call<WeatherParse>, response: Response<WeatherParse>) {
                when(response.code()){
                    // обработка положительного результата от сервера
                    200 -> if (response.isSuccessful) {
                        responseWeatherData.value = response.body()
                        Log.d("TestMsg","Request 200: ${response.body()}")
                    }
                    // обработка ошибки от сервера
                    else -> {Log.d("TestMsg","Err: ${response.errorBody()}, responce.body:${response.body()}")} /*400,401,403  | JSONObject(response.errorBody()!!.string()).getJSONObject("error").getString("message")*/
                }
            }
            override fun onFailure(call: Call<WeatherParse>, t: Throwable) {
                Log.d("TestMsg","onFailure Err: $t")// обработка ошибки соединения
            }
        })
    }

}
