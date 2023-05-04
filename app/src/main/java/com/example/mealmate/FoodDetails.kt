package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class FoodDetails : AppCompatActivity() {


    private lateinit var tvFoodId : TextView
    private lateinit var tvFoodType : TextView
    private lateinit var tvQuantity : TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        initView()
            setValuesToViews()
    }

    private fun initView(){
        tvFoodId = findViewById(R.id.tvFoodId)
        tvFoodType = findViewById(R.id.tvFoodType)
        tvQuantity = findViewById(R.id.tvFoodQuantity)
        tvDescription = findViewById(R.id.tvFoodDescription)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }


    //set values for a view in
    private fun setValuesToViews(){
        tvFoodId.text = intent.getStringExtra("foodId")
        tvFoodType.text = intent.getStringExtra("foodType")
        tvQuantity.text = intent.getStringExtra("foodQuantity")
        tvDescription.text = intent.getStringExtra("foodDescription")
    }
}