package com.example.mealmate

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class noticeDash : AppCompatActivity() {



    private lateinit var btnNoticesList: Button
    private lateinit var btnAddNotices: Button
    private lateinit var btnMyNotice: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_dash)

        btnNoticesList = findViewById(R.id.btnNoticesList)
        btnAddNotices = findViewById(R.id.btnAddNotices)
        btnMyNotice = findViewById(R.id.btnMyNotice)


        btnNoticesList.setOnClickListener {
            val intent = Intent(this, listNotices::class.java)
            startActivity(intent)
        }

        btnAddNotices.setOnClickListener {
            val intent = Intent(this, addNotice::class.java)
            startActivity(intent)
        }




        btnMyNotice.setOnClickListener {
            val intent = Intent(this, listNotices::class.java)
            startActivity(intent)
        }



    }

}