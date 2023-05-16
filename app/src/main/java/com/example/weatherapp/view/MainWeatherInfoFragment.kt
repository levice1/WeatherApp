package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.json_processing.Forecastday

class MainWeatherInfoFragment : Fragment() {

    lateinit var binding: FragmentMainWeatherInfoBinding
    private val responceForFragsDataModel: ResponceForFragsDataModel by activityViewModels()

    private lateinit var adapter: ForecastRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMainWeatherInfoBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responceForFragsDataModel.data.observe(this@MainWeatherInfoFragment) {
            FillFields().fillMainSection(it, binding)
            initRecyclerView(it.forecast.forecastday)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainWeatherInfoFragment()
    }


    private fun initRecyclerView(list: List<Forecastday>) {
        recyclerView = binding.forecastRecView
        adapter = ForecastRecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setList(list)
    }
}