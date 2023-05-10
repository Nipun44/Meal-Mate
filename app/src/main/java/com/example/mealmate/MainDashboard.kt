package com.example.mealmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainDashboard : AppCompatActivity() {
private lateinit var shareBtn : ImageView
private lateinit var mealsBtn : ImageView
private lateinit var noticeBtn : ImageView
private lateinit var myListBtn : ImageView
private lateinit var locationBtn: ImageView
private lateinit var profileButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)


        shareBtn = findViewById(R.id.ShareButton)
        mealsBtn = findViewById(R.id.MealButton)
        noticeBtn = findViewById(R.id.NoticeButton)
        myListBtn = findViewById(R.id.MyListButton)
        locationBtn = findViewById(R.id.LocationButton)
        profileButton = findViewById(R.id.ProfileButton)


        shareBtn.setOnClickListener {
            val intent = Intent(this, GivePartfood::class.java)
            startActivity(intent)
        }


        mealsBtn.setOnClickListener {
            val intent = Intent(this, FetchingFood::class.java)
            startActivity(intent)
        }

        noticeBtn.setOnClickListener {
            val intent = Intent(this, noticeDash::class.java)
            startActivity(intent)
        }

        locationBtn.setOnClickListener {
            val intent = Intent(this, LocationDashboard::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }


        myListBtn.setOnClickListener {
            val intent = Intent(this, fetchingSingleFood::class.java)
            startActivity(intent)
        }



    }



}