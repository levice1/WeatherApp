package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ApiKeyModel
import com.example.weatherapp.model.DataModel
import com.example.weatherapp.presentation.parseWeatherData
import com.example.weatherapp.viewmodel.RequestToApi
import com.example.weatherapp.viewmodel.ShowDetails


class MainActivity : AppCompatActivity() {
    val visibilitySetting by lazy { VisibilitySetting(binding) }
    val apiKey = ApiKeyModel().getApi()
    val apiUrl = ApiKeyModel().getUrl()
    val responseWeatherData: DataModel by viewModels()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //запуск слушатєля нажатий
        binding.btnFindCity.setOnClickListener {
            // обработка текста введённого пользователем
            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true)
            // если был введён город, то запрос на сервер
            if (city.isNotEmpty()){
                // спрятать кнопку и показать прогресс бар
                visibilitySetting.setInvisibleAfterPressBtn()
                // создание экземпляра класса для доступа запроса к серверу
                val requestToApi = RequestToApi(apiUrl,city,apiKey)
                // запрос на сервер
                requestToApi.getData(responseWeatherData.responceWeatherData)
                // создание слушателя, который отреагирует когда придут данные
                responseWeatherData.responceWeatherData.observe(this@MainActivity, Observer {data->
                    // что делать когда данные получены
                    parseWeatherData(data,binding, this).parse()
                    visibilitySetting.setVisibleAfterGetWeather()
                    binding.btnDetails.setOnClickListener(){
                        val showDetails = ShowDetails(data,this)
                        showDetails.start()
                    }
                })
                // если ничего не было введено
            } else {
                // тост сообщение о необходимости ввести город
                makeText(this,"Enter the city!", LENGTH_LONG).show()
                // спрятать прогресс бар и показать кнопку
                visibilitySetting.setInvisibleAfterGetErrCode()
            }
          }
        }
    }