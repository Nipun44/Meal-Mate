package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class noticeDetails : AppCompatActivity() {
    private lateinit var tvNoticeID: TextView
    private lateinit var tvNTopic: TextView
    private lateinit var tvNPlace: TextView
    private lateinit var tvNDes: TextView
    private lateinit var tvNDate: TextView

    private lateinit var btnUpdate: Button

    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_details)

        initView()
        setValuesToViews()
    }
    private fun initView(){
        tvNDate = findViewById(R.id.tvNDate)
        tvNTopic = findViewById(R.id.tvNoticeTopic)
        tvNDes = findViewById(R.id.tvNoticeDescription)
        tvNPlace = findViewById(R.id.tvNoticePlacee)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }


    private fun setValuesToViews(){
        tvNDate.text = intent.getStringExtra("nDate")
        tvNTopic.text = intent.getStringExtra("nTopic")
        tvNDes.text = intent.getStringExtra("nDescrtption")
        tvNPlace.text = intent.getStringExtra("nPlace")
//        tvNData.text = intent.getStringExtra("nData")

    }
}