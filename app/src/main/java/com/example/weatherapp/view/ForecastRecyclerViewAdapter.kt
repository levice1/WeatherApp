package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ForecastItemRecViewBinding
import com.example.weatherapp.model.json_processing.Forecastday
import com.squareup.picasso.Picasso

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
        holder.binding.txtDayTemperature.text = detailsList[position].day.avgtemp_c.toString()
        holder.binding.txtDayWeather.text = detailsList[position].day.condition.text
        getImg(
            detailsList[position].day.condition.icon,
            holder.binding.imgDay
        )
    }


    private fun getImg(url:String, imgView: ImageView) {
        Picasso.get()
            .load("https:$url") // ссылка на изображение
            .placeholder(R.drawable.weather_icon) // идентификатор ресурса для заглушки
            .resize(150, 150) // размер
            .into(imgView) // куда
    }


    fun setList(list: List<Forecastday>) {
        detailsList.addAll(list)
        notifyDataSetChanged()
    }
}