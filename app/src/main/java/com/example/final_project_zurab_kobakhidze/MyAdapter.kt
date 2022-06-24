package com.example.final_project_zurab_kobakhidze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val weatherList: ArrayList<WeatherModel>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = weatherList[position]
        holder.coverImage.setImageDrawable(currentItem.coverImage)
        holder.cityText.text = currentItem.city
        holder.titleText.text = currentItem.title
        holder.tempText.text = currentItem.temp
    }

    fun updateData(viewModels: ArrayList<WeatherModel>) {
        weatherList.clear()
        weatherList.addAll(viewModels)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val coverImage: ImageView = itemView.findViewById(R.id.imageView)
        val cityText: TextView = itemView.findViewById(R.id.cityText)
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val tempText: TextView = itemView.findViewById(R.id.tempText)
    }

}