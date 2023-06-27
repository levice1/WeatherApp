package com.example.weatherapp.view.fragments

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
import com.example.weatherapp.view_model.WeatherViewModel


class ForecastWeatherFragment : Fragment() {
    private lateinit var binding: FragmentForecastWeatherBinding

    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var adapter: ForecastRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentForecastWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initForecastRecyclerView()
        viewModel.weatherDataByCity.observe(viewLifecycleOwner) {
            updateForecastRecyclerView(it.forecast)
        }
    }

    private fun initForecastRecyclerView() {
        recyclerView = binding.forecastRecView
        adapter = ForecastRecyclerViewAdapter()
        recyclerView.adapter = adapter
    }

    private fun updateForecastRecyclerView(list: List<Forecastday>){
        adapter.setList(list)
    }
}