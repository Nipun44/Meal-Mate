package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LocationDetails : AppCompatActivity() {

    private lateinit var tvLocId : TextView
    private lateinit var tvLocName : TextView
    private lateinit var tvLocAddress : TextView
    private lateinit var tvLocDescription : TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)

        initView()
        setValuesToViews()
    }

    private fun initView(){

        tvLocId = findViewById(R.id.tvLocId)
        tvLocName = findViewById(R.id.tvLocName)
        tvLocAddress = findViewById(R.id.tvLocAddress)
        tvLocDescription = findViewById(R.id.tvLocDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }

    private fun setValuesToViews(){

        tvLocId.text = intent.getStringExtra("Location id")
        tvLocName.text = intent.getStringExtra("Location Name")
        tvLocAddress.text = intent.getStringExtra("Address")
        tvLocDescription.text = intent.getStringExtra("Description")

    }
}
