package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register : AppCompatActivity() {

    private lateinit var indReg : Button
    private lateinit var orgReg : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        indReg = findViewById(R.id.reg_ind_btn)
        orgReg = findViewById(R.id.reg_org_btn)

        indReg.setOnClickListener {
            val intent = Intent(this,RegisterAsIndividual::class.java)
            startActivity(intent)
        }

        orgReg.setOnClickListener {
            val intent = Intent(this,Register3::class.java)
            startActivity(intent)
        }
    }
}