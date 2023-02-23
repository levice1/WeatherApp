package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.viewModelFactory
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
    lateinit var binding: ActivityMainBinding
    private val responseWeatherData: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TestMsg","Start")

        binding.btnFindCity.setOnClickListener {  //запуск слушатєля нажатий
            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true) // обработка текста введённого пользователем
            Log.d("TestMsg","City OK")

            if (city.isNotEmpty()){  // если был введён город, то запрос на сервер
                Log.d("TestMsg","Not empty OK")

                visibilitySetting.setInvisibleAfterPressBtn() // спрятать кнопку и показать прогресс бар
                Log.d("TestMsg","Hide BTN")

                RequestToApi(apiUrl,city,apiKey).getData()
                Log.d("TestMsg","Request END")
                responseWeatherData.responceWeatherData.observe(this@MainActivity, Observer {
                    Log.d("TestMsg","IT: $it")
                    parseWeatherData(it,binding, View(this)).parse()
                    Log.d("TestMsg","Parse OK")
                    visibilitySetting.setVisibleAfterGetWeather()
                    Log.d("TestMsg","Show OK")
                })


            } else { // если ничего не было введено
                makeText(this,"Enter the city!", LENGTH_LONG).show() // тост сообщение о необходимости ввести город
                visibilitySetting.setInvisibleAfterGetErrCode() // спрятать прогресс бар и показать кнопку
            }
          }
        }
    }