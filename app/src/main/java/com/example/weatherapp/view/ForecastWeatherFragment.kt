package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentForecastWeatherBinding
import com.example.weatherapp.model.Forecastday
import com.example.weatherapp.view.adapter.ForecastRecyclerViewAdapter


class ForecastWeatherFragment : Fragment() {
    private lateinit var binding: FragmentForecastWeatherBinding
    private val responceForFragsDataModel: ResponceForFragsDataModel by activityViewModels()

    private lateinit var adapter: ForecastRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentForecastWeatherBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responceForFragsDataModel.data.observe(this@ForecastWeatherFragment) {
            initForecastRecyclerView(it.forecast.forecastday)
        }
    }


    private fun initForecastRecyclerView(list: List<Forecastday>) {
        recyclerView = binding.forecastRecView
        adapter = ForecastRecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setList(list)
    }
}