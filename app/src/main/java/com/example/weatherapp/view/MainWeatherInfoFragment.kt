package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.model.json_processing.Hour
import com.example.weatherapp.view.adapter.HourlyRecyclerViewAdapter
import com.example.weatherapp.viewmodel.fillMainSection
import java.util.Calendar

class MainWeatherInfoFragment : Fragment() {

    lateinit var binding: FragmentMainWeatherInfoBinding
    private val responceForFragsDataModel: ResponceForFragsDataModel by activityViewModels()

    private lateinit var adapter: HourlyRecyclerViewAdapter
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
            fillMainSection(it, binding)
            initHourlyRecView(it.forecast.forecastday[0].hour)
            scrollHourlyRecView()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainWeatherInfoFragment()
    }


    private fun initHourlyRecView(list: List<Hour>) {
        recyclerView = binding.hourlyRecView
        adapter = HourlyRecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setList(list)

    }


    // скролл RecView до текущего времени
     private fun scrollHourlyRecView(){
         val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
         recyclerView.layoutManager = LinearLayoutManager(
             this@MainWeatherInfoFragment.requireContext(),
             LinearLayoutManager.HORIZONTAL,
             false
         )
         recyclerView.scrollToPosition(hour)
         recyclerView.post {
             recyclerView.smoothScrollToPosition(hour)
         }
    }
}