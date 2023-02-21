package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.domain.models.json_processing.WeatherParse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class requestToApi(val url: String, val city: String,val key: String) {


    fun getData(handleWeatherResult: (WeatherParse?) -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
        val interfaceApi = retrofit.create(InterfaceApi::class.java)
        interfaceApi.getData(key, city, 6).enqueue(object : Callback<WeatherParse> {
            override fun onResponse(call: Call<WeatherParse>, response: Response<WeatherParse>) {
                when(response.code()){
                    // обработка положительного результата от сервера
                    200 -> if (response.isSuccessful) {
                        // TODO
                        handleWeatherResult(response.body())
                    }
                    // обработка ошибки от сервера
                    400,401,403 -> {
                        /*Toast.makeText(this@MainActivity,
                            JSONObject(response.errorBody()!!.string()).getJSONObject("error").getString("message"),
                            Toast.LENGTH_LONG
                        ).show()*/
                        //visibilitySetting.setInvisibleAfterGetErrCode() // проверить, что метод существует и вызвать его правильно
                    }
                }
            }
            override fun onFailure(call: Call<WeatherParse>, t: Throwable) {
                Log.d("TestMsg","Err $t:")// обработка ошибки соединения
            }
        })
    }

}