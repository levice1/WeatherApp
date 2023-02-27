package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ApiKeyModel
import com.example.weatherapp.model.ResponceErrLiveDataModel
import com.example.weatherapp.model.ResponceLiveDataModel
import com.example.weatherapp.presentation.parseWeatherData
import com.example.weatherapp.viewmodel.RequestToWeatherApi
import com.example.weatherapp.viewmodel.ShowDetails


class MainActivity : AppCompatActivity() {
    private val visibilitySetting by lazy { VisibilitySetting(binding) }
    private val apiKey = ApiKeyModel().getApi()
    private val apiUrl = ApiKeyModel().getUrl()
    private val responseLiveWeatherData: ResponceLiveDataModel by viewModels()
    private val responseErrorWeatherData: ResponceErrLiveDataModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        visibilitySetting.setInvisibleForStart()
        //запуск слушатєля нажатий
        binding.btnFindCity.setOnClickListener {
            // обработка текста введённого пользователем
            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true)
            // если был введён город, то запрос на сервер
            if (city.isNotEmpty()){
                // спрятать кнопку и показать прогресс бар
                visibilitySetting.setInvisibleAfterPressBtn()
                // создание экземпляра класса для доступа запроса к серверу
                val requestToWeatherApi = RequestToWeatherApi(apiUrl,city,apiKey)
                // запрос на сервер
                requestToWeatherApi.getData(responseLiveWeatherData.responceWeatherData,responseErrorWeatherData.failtureErrorCode)
                // создание слушателя, который отреагирует когда придут данные
                // код 400,401,403 (неудача)
                responseErrorWeatherData.failtureErrorCode.observe(this@MainActivity) {
                    //ОБРАБОТКА ОШИБКИ
                    makeText(this,"Error: ${it.code}, ${it.message}", LENGTH_SHORT).show()
                    visibilitySetting.setInvisibleAfterGetErrCode()
                }
                // код 200 (успех)
                responseLiveWeatherData.responceWeatherData.observe(this@MainActivity) { inputData ->
                    // что делать когда данные получены
                    parseWeatherData(inputData, binding, this).parse()
                    visibilitySetting.setVisibleAfterGetWeather()
                    // слушатель нажатий для перехода на новое активити
                    binding.btnDetails.setOnClickListener {
                        val showDetails = ShowDetails(inputData, this)
                        showDetails.start()
                    }
                }
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