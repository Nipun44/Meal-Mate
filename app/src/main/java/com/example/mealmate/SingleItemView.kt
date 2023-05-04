package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SingleItemView : AppCompatActivity() {

    private lateinit var foodName : TextView
    private lateinit var foodDesc : TextView
    private lateinit var foodQuantity: TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_item_view)

        initView()
        setValuesToView()
    }

    private fun initView(){
        foodName = findViewById(R.id.tvFoodName)
        foodDesc = findViewById(R.id.tvFoodDesc)
        foodQuantity = findViewById(R.id.tvFoodQuantity)
    }

    private fun setValuesToView(){
        foodName.text = intent.getStringExtra("foodName")
        foodDesc.text = intent.getStringExtra("foodDesc")
        foodQuantity.text = intent.getStringExtra("foodQuantity")
    }
}