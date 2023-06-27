package com.example.weatherapp.data.api_weather

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.Constants
import com.example.weatherapp.data.util.ApiKeyModel
import com.example.weatherapp.model.ErrorDataModel
import com.example.weatherapp.model.InputJsonModel
import com.example.weatherapp.model.ResponceModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QueryToWeatherApi(private val city: String) {

    private val daysCount = Constants().forecastDaysCount
    private val key = ApiKeyModel().api
    private val retrofit = Retrofit().invoke()
    private val interfaceApi = retrofit.create(InterfaceApi::class.java)
    val answerFromServer = MutableLiveData<ResponceModel>()
    fun getData() {
        interfaceApi.getData(key, city, daysCount).enqueue( object : Callback<InputJsonModel> {

            override fun onResponse(
                call: Call<InputJsonModel>,
                response: Response<InputJsonModel>
            ) {
                if (response.code() == 200) {
                    answerFromServer.value = ResponceModel.Data(response.body())
                } else {
                    answerFromServer.value = ResponceModel.Error(
                        ErrorDataModel(
                            response.code(),
                            JSONObject(response.errorBody()!!.string()).getJSONObject("error").getString("message")
                        )
                    )
                }
            }

            override fun onFailure(call: Call<InputJsonModel>, t: Throwable) {
                answerFromServer.value = ResponceModel.Error(
                    ErrorDataModel(409, "NO INTERNET CONNECTION")
                )
            }
        })
    }
}
