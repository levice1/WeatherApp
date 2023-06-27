package com.example.weatherapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.view.adapter.TabsAdapter
import com.example.weatherapp.view.fragments.DetailsWeatherInfoFragment
import com.example.weatherapp.view.fragments.ForecastWeatherFragment
import com.example.weatherapp.view.fragments.MainWeatherInfoFragment
import com.example.weatherapp.view.util.VisibilitySetting
import com.example.weatherapp.view_model.WeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val visibilitySetting = VisibilitySetting(binding)
        val viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewModel.errorFromServer
            .observe(this@MainActivity) { errData ->
                toast("Error: ${errData.code}, ${errData.message}")
                visibilitySetting.setVisibleAfterGetError()
            }

        viewModel.weatherDataByCity
            .observe(this@MainActivity) { inputData ->
                initTabs(inputData.forecast.size)
                initFragment(
                    R.id.MainFrameLayout,
                    MainWeatherInfoFragment.newInstance()
                )
                visibilitySetting.setVisibleAfterGetWeather()
            }

        binding.btnFindCity.setOnClickListener {
            hideKeyboard()
            visibilitySetting.setInvisibleAfterPressBtn()
            viewModel.init(binding.txtPlEntertCity.text.toString(), this)
        }
    }

    private fun toast(text: String) {
        makeText(this@MainActivity, text, LENGTH_SHORT).show()
    }

    private fun initFragment(idFrameLayout: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(idFrameLayout, fragment).commit()
    }

    private fun initTabs(days: Int) {
        val adapter = TabsAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(DetailsWeatherInfoFragment(), this.getString(R.string.details))
        adapter.addFragment(
            ForecastWeatherFragment(),
            "$days ${this.getString(R.string.days_weather)}"
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = adapter.titleList[position]
        }.attach()
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            binding.txtPlEntertCity.windowToken, 0
        )
    }
}
