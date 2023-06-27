package com.example.weatherapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastItemRecViewBinding
import com.example.weatherapp.model.Forecastday
import com.example.weatherapp.network.getImgFromUrl

class ForecastRecyclerViewAdapter : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ForecastItemRecViewBinding): RecyclerView.ViewHolder(binding.root)


    private var detailsList = ArrayList<Forecastday>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ForecastItemRecViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun getItemCount(): Int {
        return detailsList.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = detailsList[position].date.split("-")
        holder.binding.txtDate.text = "${date[2]}.${date[1]}"
        holder.binding.txtDayMaxTemperature.text = detailsList[position].day.maxtemp_c.toInt().toString()
        holder.binding.txtDayMinTemperature.text = detailsList[position].day.mintemp_c.toInt().toString()
        holder.binding.txtDayWeather.text = detailsList[position].day.condition.text
        getImgFromUrl(
            detailsList[position].day.condition.icon,
            holder.binding.imgDay
        )
    }





    fun setList(list: List<Forecastday>) {
        detailsList.addAll(list)
        notifyDataSetChanged()
    }
}