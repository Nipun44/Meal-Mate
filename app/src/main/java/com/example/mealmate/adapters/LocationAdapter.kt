package com.example.mealmate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.models.LocationModel

class LocationAdapter(private val locationList: ArrayList<LocationModel>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.location_list,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
        val currentLocation = locationList[position]
        holder.tvLocName.text = currentLocation.locationName
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    class ViewHolder(itemView: View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvLocName : TextView = itemView.findViewById(R.id.tvLocName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}