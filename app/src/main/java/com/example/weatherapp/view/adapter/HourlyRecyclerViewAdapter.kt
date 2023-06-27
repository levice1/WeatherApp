package com.example.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.HourlyItemRecViewBinding
import com.example.weatherapp.model.Hour
import com.example.weatherapp.network.getImgFromUrl

class HourlyRecyclerViewAdapter: RecyclerView.Adapter<HourlyRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HourlyItemRecViewBinding): RecyclerView.ViewHolder(binding.root)


    private var detailsList = ArrayList<Hour>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HourlyItemRecViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: HourlyRecyclerViewAdapter.ViewHolder, position: Int) {
        val time = detailsList[position].time.substring(detailsList[position].time.length - 5)
        holder.binding.txtTime.text = time
        holder.binding.txtHourTemperature.text = detailsList[position].temp_c.toString()
        holder.binding.txtHourWeather.text = detailsList[position].condition.text
        getImgFromUrl(
            detailsList[position].condition.icon,
            holder.binding.imgHour
        )
    }


    override fun getItemCount(): Int {
        return detailsList.size
    }


    fun setList(list: List<Hour>) {
        detailsList.addAll(list)
        notifyDataSetChanged()
    }
}