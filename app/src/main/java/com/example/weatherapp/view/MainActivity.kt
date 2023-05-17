package com.example.weatherapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import android.widget.Toast.*
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ApiKeyModel
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.ResponceErrLiveDataModel
import com.example.weatherapp.model.ResponceLiveDataModel
import com.example.weatherapp.view.adapter.TabsAdapter
import com.example.weatherapp.viewmodel.VisibilitySetting
import com.example.weatherapp.viewmodel.network.RequestToWeatherApi
import com.example.weatherapp.viewmodel.network.sendMessageToTG
import com.google.android.material.tabs.TabLayoutMediator


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
        // TABS
        initTabs()
        //запуск слушатєля нажатий
        binding.btnFindCity.setOnClickListener {
            // скрыть клавиатуру
            hideKeyboard()
            // спрятать кнопку и показать прогресс бар
            visibilitySetting.setInvisibleAfterPressBtn()
            // обработка текста введённого пользователем
            val city = verificationCityField(binding.txtPlEntertCity.editableText)
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
                responseErrorWeatherData.failtureErrorCode
                    .observe(this@MainActivity) {
                            errData ->
                            //ОБРАБОТКА ОШИБКИ
                            // отправка сообщения в ТГ если проблема с API ключем
                            if (errData.code == 401 || errData.code == 403) sendMessageToTG("WEATHER APP: ${errData.message}")
                            // вывод сообщения об ошибке
                            makeText(this, "Error: ${errData.code}, ${errData.message}", LENGTH_SHORT).show()
                            visibilitySetting.setVisibleAfterGetError()
                }
                // код 200 (успех)
                responseLiveWeatherData.responceWeatherData
                    .observe(this@MainActivity) {
                            inputData ->
                            // что делать когда данные получены
                            initFragment(
                                R.id.MainFrameLayout,
                                MainWeatherInfoFragment.newInstance()
                            )
                        weatherDataForFrags.data.value = inputData
                        visibilitySetting.setVisibleAfterGetWeather()
                    }
                // если ничего не было введено
            } else {
                // тост сообщение о необходимости ввести город
                makeText(this, "Enter the city!", LENGTH_LONG).show()
                // спрятать прогресс бар и показать кнопку
                visibilitySetting.setVisibleAfterGetError()
            }
        }
    }

    private fun initFragment(idFrameLayout: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(idFrameLayout, fragment).commit()
    }

    private fun initTabs(){
        val adapter = TabsAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(DetailsWeatherInfoFragment(), "Details")
        adapter.addFragment(ForecastWeatherFragment(), "10 Day Weather")
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = adapter.titleList[position]
        }.attach()
    }


    // скрыть клавиатуру
    private fun hideKeyboard(){
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            binding.txtPlEntertCity.windowToken, 0
        )
    }

    private fun verificationCityField(input:Editable): String {
        return input
            .toString()
            .lowercase()
            .trim()
            .replace(" ", "+", true)
    }
}