package com.example.mealmate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.models.FoodModel

class FoodAdapter(private val foodList: ArrayList<FoodModel>):
    RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.tvFoodName.text = currentFood.typeFood
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFoodName : TextView = itemView.findViewById(R.id.tvFoodName)
    }

}