package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast.*
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.domain.ShowDetails
import com.example.weatherapp.domain.VisibilitySetting
import com.example.weatherapp.domain.models.ApiKeyModel
import com.example.weatherapp.domain.models.json_processing.WeatherParse
import com.example.weatherapp.domain.parseWeatherData
import com.example.weatherapp.domain.requestToApi


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val visibilitySetting = VisibilitySetting(binding)
        val apiKey = ApiKeyModel().getApi()
        val apiUrl = ApiKeyModel().getUrl()
        //ОСНОВНАЯ РАБОТА!!!
        visibilitySetting.setInvisibleForStart() // для удобства разработки
        Log.d("TestMsg","Start")
        binding.btnFindCity.setOnClickListener {  //запуск слушатєля нажатий

            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true) // обработка текста введённого пользователем
            Log.d("TestMsg","City OK")

            if (city.isNotEmpty()){  // если был введён город, то запрос на сервер
                Log.d("TestMsg","Not empty OK")

                visibilitySetting.setInvisibleAfterPressBtn() // спрятать кнопку и показать прогресс бар
                Log.d("TestMsg","Hide BTN")

                val requestToApi = requestToApi(apiUrl,city,apiKey) // сам запрос
                val requestData = requestToApi.getData()
                Log.d("TestMsg","Api request OK")
                fun handleWeatherResult(result: WeatherParse?) {
                    if (result != null) {
                        parseWeatherData(result, binding, View(this)).parse()
                        // здесь можно использовать полученный результат
                        // например, отобразить погоду в пользовательском интерфейсе
                    } else {
                        // здесь можно обработать ошибку
                    }
                }
            }
                parseWeatherData(requestData, binding, View(this)).parse()
                Log.d("TestMsg","Parse OK")

                // включение видимости полей с данными на экране
                visibilitySetting.setVisibleAfterGetWeather()
                Log.d("TestMsg","Show OK")

                binding.btnDetails.setOnClickListener(){
                    ShowDetails(requestData,this)
                }

            } else { // если ничего не было введено
                makeText(this,"Enter the city!", LENGTH_LONG).show() // тост сообщение о необходимости ввести город
                visibilitySetting.setInvisibleAfterGetErrCode() // спрятать прогресс бар и показать кнопку
            }
          }
        }
    }