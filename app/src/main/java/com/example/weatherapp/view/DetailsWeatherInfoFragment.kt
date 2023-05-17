package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentDetailsWeatherInfoBinding
import com.example.weatherapp.model.ResponceForFragsDataModel
import com.example.weatherapp.viewmodel.ParseDetailWeatherData
import com.example.weatherapp.viewmodel.fillDetailsSection


class DetailsWeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailsWeatherInfoBinding

    private val responseForFragsDataModel: ResponceForFragsDataModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = FragmentDetailsWeatherInfoBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responseForFragsDataModel.data.observe(this@DetailsWeatherInfoFragment) {
            val detailsItems = ParseDetailWeatherData().getDetails(it, this@DetailsWeatherInfoFragment.requireContext())
           fillDetailsSection(detailsItems, binding)
        }

    }


//    companion object {
//        @JvmStatic
//        fun newInstance() = DetailsWeatherInfoFragment()
//    }
}