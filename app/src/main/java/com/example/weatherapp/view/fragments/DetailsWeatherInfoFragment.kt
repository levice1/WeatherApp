package com.example.weatherapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentDetailsWeatherInfoBinding
import com.example.weatherapp.view.util.fillFieldsInDetailsFragment
import com.example.weatherapp.view_model.WeatherViewModel


class DetailsWeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailsWeatherInfoBinding

    private val viewModel: WeatherViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = FragmentDetailsWeatherInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherDataByCity.observe(viewLifecycleOwner) {
            fillFieldsInDetailsFragment(it.details, binding)
        }
    }
}