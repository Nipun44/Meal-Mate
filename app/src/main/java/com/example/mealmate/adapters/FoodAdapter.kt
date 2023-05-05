//package com.example.mealmate.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.mealmate.R
//import com.example.mealmate.models.FoodModel
//
//
//
//
//class FoodAdapter(private val foodList: ArrayList<FoodModel>) :
//    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
//
//    private lateinit var mListener: onItemClickListener
//
//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(clickListener: onItemClickListener){
//        mListener = clickListener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item, parent, false)
//        return ViewHolder(itemView, mListener)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentFood = foodList[position]
//        holder.tvFoodName.text = currentFood.typeFood
//    }
//
//    override fun getItemCount(): Int {
//        return foodList.size
//    }
//
//    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
//
//        val tvFoodName : TextView = itemView.findViewById(R.id.tvFoodName)
//
//        init {
//            itemView.setOnClickListener {
//                clickListener.onItemClick(adapterPosition)
//            }
//        }
//
//    }
//
//}
//




package com.example.mealmate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.models.FoodModel




class FoodAdapter(private val foodList: ArrayList<FoodModel>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.tvFoodName.text = currentFood.typeFood
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvFoodName : TextView = itemView.findViewById(R.id.tvFoodName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}


