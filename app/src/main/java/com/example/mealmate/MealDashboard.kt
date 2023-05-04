package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MealDashboard : AppCompatActivity() {

    private lateinit var iHaveExtraBtn: Button
    private lateinit var doYouHaveExtraBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_dashboard)

        iHaveExtraBtn = findViewById(R.id.iHaveExtraBtn)
        doYouHaveExtraBtn = findViewById(R.id.doYouHaveExtraBtn)

        iHaveExtraBtn.setOnClickListener {
            val intent = Intent(this, GivePartFood::class.java)
            startActivity(intent)
        }

        doYouHaveExtraBtn.setOnClickListener {
            val intent = Intent(this,FetchingFood::class.java)
            startActivity(intent)
        }
    }
}