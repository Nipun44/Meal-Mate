package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LocationDashboard : AppCompatActivity() {
    private lateinit var btnAdd : Button
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_dashboard)


        btnAdd = findViewById(R.id.addbtn)
        btnView = findViewById(R.id.viewbtn)

        btnAdd.setOnClickListener {
            val intent = Intent(this,AddLocation::class.java)
            startActivity(intent)
        }
        btnView.setOnClickListener {
            val intent = Intent(this,FetchLocation::class.java)
            startActivity(intent)
        }
    }
}