package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.json_processing.WeatherParse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //val API_URL = "http://api.weatherapi.com/"
    //val API_KEY = "a2c054487d1040eb8fe145528232601"
    private val apiKey = ApiKey().getApi()
    private val apiUrl = ApiKey().getUrl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val visibilitySetting = VisibilitySetting(binding)

        // функция включения/отключения видимости полей на главном экране
        fun setVisible(arg:Boolean){
            if (arg){
                binding.mainLayout.setVisibility(View.VISIBLE)
                binding.forecastLayout.setVisibility(View.VISIBLE)
                binding.btnFindCity.setVisibility(View.VISIBLE)
                binding.progressBar.setVisibility(View.INVISIBLE)
            } else {
                binding.btnFindCity.setVisibility(View.INVISIBLE)
                binding.progressBar.setVisibility(View.VISIBLE)
            }
        }

        // функция обработки Json файла с данными погоды, и вывода их на активити
        fun parseWeatherData(responceData: WeatherParse){
            binding.txtCityName.text = "${responceData.location.name}, ${responceData.location.country}"
            // temperatura
            //now
            binding.txtTemperature.text = "${responceData.current.temp_c}°C"
            //2day
            val temp2Day = responceData.forecast.forecastday[1].day.avgtemp_c
            val txtTemp2DayId = findViewById<TextView>(R.id.txt2DayTemperature)
            txtTemp2DayId.text = "$temp2Day°C"
            //3day
            val temp3Day = responceData.forecast.forecastday[2].day.avgtemp_c
            val txtTemp3DayId = findViewById<TextView>(R.id.txt3DayTemperature)
            txtTemp3DayId.text = "$temp3Day°C"
            //4day
            val temp4Day = responceData.forecast.forecastday[3].day.avgtemp_c
            val txtTemp4DayId = findViewById<TextView>(R.id.txt4DayTemperature)
            txtTemp4DayId.text = "$temp4Day°C"
            //temperature feels like now
            binding.txtFeelsLike.text = "Feels like ${responceData.current.feelslike_c.toString()}°C"
            //min temperature now
            binding.txtMinTemperature.text = "Min ${responceData.forecast.forecastday[0].day.mintemp_c}°C"
            //max temperature now
            binding.txtMaxTemperature.text = "Max ${responceData.forecast.forecastday[0].day.maxtemp_c}°C"
            // weather
            //now
            binding.txtWeather.text = responceData.current.condition.text
            //2day
            val txtWeather2DayId = findViewById<TextView>(R.id.txt2DayWeather)
            txtWeather2DayId.text = responceData.forecast.forecastday[1].day.condition.text
            //3day
            val txtWeather3DayId = findViewById<TextView>(R.id.txt3DayWeather)
            txtWeather3DayId.text = responceData.forecast.forecastday[2].day.condition.text
            //4day
            val txtWeather4DayId = findViewById<TextView>(R.id.txt4DayWeather)
            txtWeather4DayId.text = responceData.forecast.forecastday[3].day.condition.text
            //day
            //2day
            val date2Day = responceData.forecast.forecastday[1].date.split("-")
            val txtDate2DayId = findViewById<TextView>(R.id.txt2DayLabel)
            txtDate2DayId.text = "${date2Day[2]}-${date2Day[1]}"
            //3day
            val date3Day = responceData.forecast.forecastday[2].date.split("-")
            val txtDate3DayId = findViewById<TextView>(R.id.txt3DayLabel)
            txtDate3DayId.text = "${date3Day[2]}-${date3Day[1]}"
            //4day
            val date4Day = responceData.forecast.forecastday[3].date.split("-")
            val txtDate4DayId = findViewById<TextView>(R.id.txt4DayLabel)
            txtDate4DayId.text = "${date4Day[2]}-${date4Day[1]}"
             // включение видимости полей с данными на экране
                //setVisible(true)
            visibilitySetting.setVisibleAfterGetWeather()
        }

        // функция запроса на сервер для получения данных
        fun requestToApi(url:String, city:String,key:String) {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val myApi = retrofit.create(MyApi::class.java)
            myApi.getData(key, city, 4).enqueue(object : Callback<WeatherParse> {
                override fun onResponse(call: Call<WeatherParse>, response: Response<WeatherParse>) {
                   when(response.code()){
                       // обработка положительного результата от сервера
                       200 -> if (response.isSuccessful) { parseWeatherData(response.body()!!) }
                       // обработка ошибки от сервера
                       400 -> { Toast.makeText(this@MainActivity,
                           "Err: ${JSONObject(response.errorBody()!!.string()).getJSONObject("error").getString("message")}",
                           LENGTH_LONG).show()
                           visibilitySetting.setInvisibleAfterGetErrCode()  }
                    }
                }
                override fun onFailure(call: Call<WeatherParse>, t: Throwable) {
                    Log.d("TestMsg","Err $t:")// обработка ошибки соединения
                }
            })
        }
        //ОСНОВНАЯ РАБОТА!!!
        //запуск слушатєля нажатий
        binding.mainLayout.setVisibility(View.INVISIBLE)  //  временно!!
        binding.forecastLayout.setVisibility(View.INVISIBLE)  // временно!!
        binding.btnFindCity.setOnClickListener {
            // обробка введеного пользователем текста
            val city = binding.txtPlEntertCity.text.toString().lowercase().trim().replace(" ","+",true)
            // если был введён город, то запрос на сервер
            if (city.isNotEmpty()){
                setVisible(false)
                requestToApi(apiUrl,city,apiKey)
            // если нет то вспливающее сообщение о необходимости ввести город
            } else {
                makeText(this,"Enter the city!", LENGTH_LONG).show()
                visibilitySetting.setInvisibleAfterGetErrCode()
            }
          }
        }
    }







/* стара функція запросу по api, також робоча, використовується разом з функією ParseWeatherData
fun requestToApi(url:String){
    val queue = Volley.newRequestQueue(this)
    val request = StringRequest( Request.Method.GET, url,
        { res -> parseWeatherData(res) /*setWeatherData(res)*/ },
        { errors -> Toast.makeText(this,"Err: $errors",Toast.LENGTH_LONG).show() })
    queue.add(request)
}

 // функція обробки Json файлу з данними погоди, і виводу на актівіті
        fun parseWeatherData2 (result :String){

            //city and country
            val city = JSONObject(result).getJSONObject("location").getString("name")
            val country = JSONObject(result).getJSONObject("location").getString("country")
            binding.txtCityName.text = "$city, $country"

            // temperatura
              //now
            val tempNow = JSONObject(result).getJSONObject("current").getString("temp_c")
            binding.txtTemperature.text = "$tempNow°C"
              //2day
            val temp2Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1).getJSONObject("day").getString("avgtemp_c")
            val txtTemp2DayId = findViewById<TextView>(R.id.txt2DayTemperature)
            txtTemp2DayId.text = "$temp2Day°C"
              //3day
            val temp3Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2).getJSONObject("day").getString("avgtemp_c")
            val txtTemp3DayId = findViewById<TextView>(R.id.txt3DayTemperature)
            txtTemp3DayId.text = "$temp3Day°C"
              //4day
            val temp4Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(3).getJSONObject("day").getString("avgtemp_c")
            val txtTemp4DayId = findViewById<TextView>(R.id.txt4DayTemperature)
            txtTemp4DayId.text = "$temp4Day°C"


            //temperature feels like now
            val temperatureFeelsLikeNow = JSONObject(result).getJSONObject("current").getString("feelslike_c")
            binding.txtFeelsLike.text = "Feels like $temperatureFeelsLikeNow°C"


            //min temperature now
            val minTemperatureNow = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getString("mintemp_c")
            binding.txtMinTemperature.text = "Min $minTemperatureNow°C"


            //max temperature now
            val maxTemperatureNow = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getString("maxtemp_c")
            binding.txtMaxTemperature.text = "Max $maxTemperatureNow°C"


            // weather
              //now
            val weatherNow = JSONObject(result).getJSONObject("current").getJSONObject("condition").getString("text")
            binding.txtWeather.text = weatherNow
              //2day
            val weather2Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1).getJSONObject("day").getJSONObject("condition").getString("text")
            val txtWeather2DayId = findViewById<TextView>(R.id.txt2DayWeather)
            txtWeather2DayId.text = weather2Day
              //3day
            val weather3Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2).getJSONObject("day").getJSONObject("condition").getString("text")
            val txtWeather3DayId = findViewById<TextView>(R.id.txt3DayWeather)
            txtWeather3DayId.text = weather3Day
              //4day
            val weather4Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(3).getJSONObject("day").getJSONObject("condition").getString("text")
            val txtWeather4DayId = findViewById<TextView>(R.id.txt4DayWeather)
            txtWeather4DayId.text = weather4Day

            //day
              //2day
            val date2Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1).getString("date").split("-")
            val txtDate2DayId = findViewById<TextView>(R.id.txt2DayLabel)
            txtDate2DayId.text = "${date2Day[2]}-${date2Day[1]}"
              //3day
            val date3Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2).getString("date").split("-")
            val txtDate3DayId = findViewById<TextView>(R.id.txt3DayLabel)
            txtDate3DayId.text = "${date3Day[2]}-${date3Day[1]}"
              //4day
            val date4Day = JSONObject(result).getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(3).getString("date").split("-")
            val txtDate4DayId = findViewById<TextView>(R.id.txt4DayLabel)
            txtDate4DayId.text = "${date4Day[2]}-${date4Day[1]}"

            setVisible(true)
        }
 */


