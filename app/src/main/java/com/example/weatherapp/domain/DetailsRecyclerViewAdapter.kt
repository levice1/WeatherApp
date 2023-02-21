package com.example.weatherapp.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.DetailsItemLayoutBinding
import com.example.weatherapp.domain.models.DetailDataModel

class DetailsRecyclerViewAdapter : RecyclerView.Adapter<DetailsRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: DetailsItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private var detailsList = ArrayList<DetailDataModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DetailsItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return detailsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgLabelItem.setImageResource(detailsList[position].iconId)
        holder.binding.txtLabelItem.text = detailsList[position].labelText
        holder.binding.txtDataItem.text = detailsList[position].mainData
        if (detailsList[position].additionalText == ""){
            holder.binding.txtDescriptionItem.visibility = View.GONE
        }else holder.binding.txtDescriptionItem.text = detailsList[position].additionalText
    }

    fun setList(list: ArrayList<DetailDataModel>) {
        detailsList.addAll(list)
        notifyDataSetChanged()
    }
}