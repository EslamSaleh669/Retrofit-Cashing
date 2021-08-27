package com.example.alamyatask.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alamyatask.R
import com.example.alamyatask.data.network.response.ListItem


class WeatherAdapter(
    private val Weather: ArrayList<ListItem?>?,
    val activity: Activity
) : RecyclerView.Adapter<WeatherAdapter.WeatherVHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVHolder =
        WeatherVHolder(
          LayoutInflater.from(parent.context)
        .inflate(R.layout.weather_viewshape,parent,false)
    )



    override fun onBindViewHolder(holder: WeatherVHolder, position: Int) {

     holder.WeatherTitle.text = Weather!!.get(position)!!.weather!![0]!!.main
     holder.WeatherDesc.text = Weather.get(position)!!.weather!![0]!!.description
     holder.WeatherDate.text = Weather.get(position)!!.dtTxt

        if (Weather.get(position)!!.weather!![0]!!.main!!.contains("Cloud")){
            holder.WeatherImage.setImageResource(R.drawable.ic_cloudy)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Rain")){
            holder.WeatherImage.setImageResource(R.drawable.ic_rainy)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Sun")){
            holder.WeatherImage.setImageResource(R.drawable.ic_sunny)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Cold")){
            holder.WeatherImage.setImageResource(R.drawable.ic_coldly)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Warm")){
            holder.WeatherImage.setImageResource(R.drawable.ic_warlmly)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Extreme")){
            holder.WeatherImage.setImageResource(R.drawable.ic_extreme_weather)
        }else if (Weather.get(position)!!.weather!![0]!!.main.equals("Clear")){
            holder.WeatherImage.setImageResource(R.drawable.ic_clearly)
        }else{
            holder.WeatherImage.setImageResource(R.drawable.ic_sunny)

        }





     }

    override fun getItemCount() = Weather!!.size


    class WeatherVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val WeatherTitle : TextView =itemView.findViewById(R.id.weathertitle)
       val WeatherDesc : TextView =itemView.findViewById(R.id.weatherdescription)
       val WeatherDate : TextView =itemView.findViewById(R.id.weatherdate)
       val WeatherImage : ImageView =itemView.findViewById(R.id.weatherImage)


    }

    fun addItems(moreweather:ArrayList<ListItem?>){
        this.Weather!!.addAll(moreweather)
        notifyDataSetChanged()
    }


}