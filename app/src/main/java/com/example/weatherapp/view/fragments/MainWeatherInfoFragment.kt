package com.example.weatherapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentMainWeatherInfoBinding
import com.example.weatherapp.model.Hour
import com.example.weatherapp.view.adapter.HourlyRecyclerViewAdapter
import com.example.weatherapp.view.util.fillFieldsInMainFragment
import com.example.weatherapp.view_model.WeatherViewModel
import java.util.Calendar

class MainWeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentMainWeatherInfoBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var adapter: HourlyRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMainWeatherInfoBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherDataByCity.observe(viewLifecycleOwner) {
            fillFieldsInMainFragment(it.main, binding)
            initHourlyRecView(it.forecast[0].hour)
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


    // scroll recycler view to current time in device
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