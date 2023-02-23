package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ApiKeyModel
import com.example.weatherapp.model.DataModel
import com.example.weatherapp.presentation.parseWeatherData
import com.example.weatherapp.viewmodel.RequestToApi


class MainActivity : AppCompatActivity() {
    val visibilitySetting by lazy { VisibilitySetting(binding) }
    lateinit var city:String
    val apiKey = ApiKeyModel().getApi()
    val apiUrl = ApiKeyModel().getUrl()
    val responseWeatherData: DataModel by viewModels()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFindCity.setOnClickListener {  //запуск слушатєля нажатий
            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true) // обработка текста введённого пользователем

            if (city.isNotEmpty()){  // если был введён город, то запрос на сервер

                visibilitySetting.setInvisibleAfterPressBtn() // спрятать кнопку и показать прогресс бар

                val requestToApi = RequestToApi(apiUrl,city,apiKey)
                requestToApi.getData()
                responseWeatherData.responceWeatherData.observe(this@MainActivity, Observer {
                    parseWeatherData(it,binding, View(this)).parse()
                    visibilitySetting.setVisibleAfterGetWeather()
                })
            } else { // если ничего не было введено
                makeText(this,"Enter the city!", LENGTH_LONG).show() // тост сообщение о необходимости ввести город
                visibilitySetting.setInvisibleAfterGetErrCode() // спрятать прогресс бар и показать кнопку
            }
          }
        }
    }