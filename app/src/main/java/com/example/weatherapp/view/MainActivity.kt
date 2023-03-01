package com.example.weatherapp.view

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ApiKeyModel
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.ResponceErrLiveDataModel
import com.example.weatherapp.model.ResponceLiveDataModel
import com.example.weatherapp.viewmodel.RequestToWeatherApi


class MainActivity : AppCompatActivity() {

    private val apiKey = ApiKeyModel().getApi()
    private val apiUrl = ApiKeyModel().getUrl()
    private val responseLiveWeatherData: ResponceLiveDataModel by viewModels()
    private val responseErrorWeatherData: ResponceErrLiveDataModel by viewModels()

    private val weatherDataForFrags: ResponceForFragsDataModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val visibilitySetting = VisibilitySetting(binding)
        //запуск слушатєля нажатий
        binding.btnFindCity.setOnClickListener {
            // КОЛХОЗ НО РАБОТАЕТ! (скрыть клавиатуру)
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                binding.txtPlEntertCity.windowToken, 0
            )
            // спрятать кнопку и показать прогресс бар
            visibilitySetting.setInvisibleAfterPressBtn()
            // обработка текста введённого пользователем
            val city =
                binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ", "+", true)
            // если был введён город, то запрос на сервер
            if (city.isNotEmpty()) {
                // создание экземпляра класса для доступа запроса к серверу
                val requestToWeatherApi = RequestToWeatherApi(apiUrl, city, apiKey)
                // запрос на сервер
                requestToWeatherApi.getData(
                    responseLiveWeatherData.responceWeatherData,
                    responseErrorWeatherData.failtureErrorCode
                )
                // создание слушателя, который отреагирует когда придут данные
                // код 400,401,403 (неудача)
                responseErrorWeatherData.failtureErrorCode.observe(this@MainActivity) { errData ->
                    //ОБРАБОТКА ОШИБКИ
                    makeText(
                        this, "Error: ${errData.code}, ${errData.message}", LENGTH_SHORT
                    ).show()
                    visibilitySetting.setVisibleAfterGetError()
                }
                // код 200 (успех)
                responseLiveWeatherData.responceWeatherData.observe(this@MainActivity) { inputData ->
                    // что делать когда данные получены
                    initFragment(R.id.MainFrameLayout, MainWeatherInfoFragment.newInstance())
                    initFragment(R.id.DetailsFrameLayout, DetailsWeatherInfoFragment.newInstance())
                    weatherDataForFrags.data.value = inputData
                    visibilitySetting.setVisibleAfterGetWeather()
                }
                // если ничего не было введено
            } else {
                // тост сообщение о необходимости ввести город
                makeText(this, "Enter the city!", LENGTH_LONG).show()
                visibilitySetting.setVisibleAfterGetWeather()
                // спрятать прогресс бар и показать кнопку
            }
        }
    }

    fun initFragment(idFrameLayout: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(idFrameLayout, fragment).commit()
    }
}